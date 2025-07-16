package main.java.com.taskManagementApp.managers;

import main.java.com.taskManagementApp.enums.TaskStatus;
import main.java.com.taskManagementApp.enums.WorkloadStrategyType;
import main.java.com.taskManagementApp.exceptions.TaskNotFoundException;
import main.java.com.taskManagementApp.factories.WorkloadStrategyTypeFactory;
import main.java.com.taskManagementApp.models.*;
import main.java.com.taskManagementApp.notifications.NotificationManager;
import main.java.com.taskManagementApp.strategies.WorkloadStrategy;
import main.java.com.taskManagementApp.exceptions.InvalidTaskInputsException;
import main.java.com.taskManagementApp.exceptions.TaskCreationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// can create an interface for this and later on use different Manager class implementing them
public class TaskManager {
    private static volatile TaskManager INSTANCE;
    private final Map<String, TaskEntity> tasks;
    private final WorkloadStrategy workloadStrategy;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final NotificationManager notificationManager;

    private TaskManager(WorkloadStrategyType workloadStrategyType) {
        this.tasks = new ConcurrentHashMap<>();
        this.notificationManager = NotificationManager.getInstance();
        this.workloadStrategy = WorkloadStrategyTypeFactory.selectWorkloadStrategyType(workloadStrategyType);

    }

    public static TaskManager getInstance(WorkloadStrategyType workloadStrategyType){
        if(INSTANCE == null){
            synchronized (TaskManager.class){
                if(INSTANCE == null){
                    INSTANCE = new TaskManager(workloadStrategyType);
                }
            }
        }
        return INSTANCE;
    }

    public void addObserver(Observer observer){
        notificationManager.addObserver(observer);
    }


    public Task createTask(String title, String description, LocalDate deadline, User user) {
        Objects.requireNonNull(user, "User cannot be null");
        if (!validateTaskCreation(title, description, deadline)){
            return null;
        }

        Task task = new Task(title, description, deadline, user);
        tasks.putIfAbsent(task.getId(), task);
        UserManager.getInstance().addTaskToUser(user, task);

        notificationManager.notifyObservers("Task created: " + task.getTitle());
        return task;
    }

    // Create a subtask under a parent task
    public Subtask createSubtask(String parentTaskId, String title, String description, LocalDate deadline, User user) {
        Objects.requireNonNull(user, "User cannot be null");
        if (!validateTaskCreation(title, description, deadline)){
            return null;
        }
        try {
            TaskEntity parentTask = Optional.ofNullable(tasks.get(parentTaskId))
                    .orElseThrow(() -> new TaskNotFoundException("Parent task not found"));

            if (!(parentTask instanceof Task)) {
                throw new IllegalArgumentException("Subtasks can only be added to Tasks");
            }
            Subtask subtask = new Subtask(title, description, deadline, user);
            tasks.put(subtask.getId(), subtask);

            // Add subtask to parent task
            ((Task) parentTask).addSubtask(subtask);

            UserManager.getInstance().addTaskToUser(user, subtask);

            notificationManager.notifyObservers("Subtask created: " + subtask.getTitle() + " for the task " + parentTask.getTitle());
            return subtask;
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Create a story with optional tasks
    public Story createStory(String title, String description, LocalDate deadline, User user, List<Task> relatedTasks) {
        if (!validateTaskCreation(title, description, deadline)){
            return null;
        }

        Story story = new Story(title, description, deadline, user);

        // Add related tasks to the story
        if (relatedTasks != null) {
            relatedTasks.forEach(story::addTask);
        }

        tasks.put(story.getId(), story);
        UserManager.getInstance().addTaskToUser(user, story);

        notificationManager.notifyObservers("Story created: " + story.getTitle());
        return story;
    }


    private boolean validateTaskCreation(String title, String description, LocalDate deadline) {
        try{
            if (title == null || title.trim().isEmpty()) {
                throw new InvalidTaskInputsException("Task title cannot be empty");
            }
            if (description == null || description.trim().isEmpty()) {
                throw new InvalidTaskInputsException("Task description cannot be empty");
            }
            if (deadline == null || deadline.isBefore(LocalDate.now())) {
                throw new InvalidTaskInputsException("Invalid deadline");
            }
            return true;
        }
        catch (InvalidTaskInputsException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    // Could have used strategy pattern for moving task

    public void moveTask(String taskId, String newParentId) {
        rwLock.writeLock().lock();
        try {
            TaskEntity task = tasks.get(taskId);
            TaskEntity newParent = tasks.get(newParentId);

            if (newParent == null || task == null) {
                throw new TaskNotFoundException("Invalid task or parent ID");
            }

            if (newParent instanceof Task && task instanceof Subtask) {
                ((Task) newParent).addSubtask((Subtask) task);
                // need to remove task as well
            } else if (newParent instanceof Story && task instanceof Task) {
                ((Story) newParent).addTask((Task) task);
            } else {
                throw new IllegalArgumentException("Invalid move operation");
            }

            notificationManager.notifyObservers("Task moved: " + task.getTitle() + " to new parent: " + newParent.getTitle());
        } catch (TaskNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void updateTask(String taskId, TaskUpdateFieldCriteria updateFieldCriteria){
        rwLock.writeLock().lock(); // Lock the task for writing
        try {
            TaskEntity task = tasks.get(taskId);

            if (task == null) {
                throw new TaskNotFoundException("Task not found with ID: " + taskId);
            }

            // Update fields based on the TaskUpdateFieldCriteria
            if (updateFieldCriteria.getTitle() != null) {
                task.setTitle(updateFieldCriteria.getTitle());
            }
            if (updateFieldCriteria.getDescription() != null) {
                task.setDescription(updateFieldCriteria.getDescription());
            }
            if (updateFieldCriteria.getDeadline() != null) {
                task.setDeadline(updateFieldCriteria.getDeadline());
            }
            if (updateFieldCriteria.getAssignedUser() != null) {
                task.setAssignedUser(updateFieldCriteria.getAssignedUser());
            }
            if (updateFieldCriteria.getStatus() != null) {
                task.setStatus(updateFieldCriteria.getStatus());
            }

            // Notify observers about the update
            notificationManager.notifyObservers("Task updated: " + task.getTitle());

        } catch (TaskNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            rwLock.writeLock().unlock(); // Unlock the task after updating
        }

    }

    public Map<TaskStatus, Integer> getUserWorkload(User user) {
        return workloadStrategy.calculateWorkload(user);
    }

    public TaskEntity getTask(String taskId) {
        return tasks.get(taskId);
    }
}
