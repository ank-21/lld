package main.java.com.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskManager {
    private Map<String, Task> tasks;
    private Map<String, List<Task>> tagIndex;
    private List<TaskAction> taskActions;
    private static volatile TaskManager INSTANCE;

    private TaskManager(){
        tasks = new ConcurrentHashMap<>();
        tagIndex = new ConcurrentHashMap<>();
        taskActions = new ArrayList<>();
    }

    public static TaskManager getInstance(){
        if(INSTANCE == null){
            synchronized (TaskManager.class){
                if(INSTANCE == null){
                    return INSTANCE = new TaskManager();
                }
            }
        }
        return INSTANCE;
    }

    // Method to register observers
    public void registerObserver(TaskAction action) {
        taskActions.add(action);
    }

    // Method to remove observers
    public void removeObserver(TaskAction action) {
        taskActions.remove(action);
    }

    public Task addTask(String name, String description, List<String> tags, LocalDateTime deadline, double storyPoints){
        Task task = new Task(name, description, tags, deadline, storyPoints);
        Task existingTask = tasks.putIfAbsent(name, task);
        if(existingTask == null){
            for(String tag : tags){
                tagIndex.computeIfAbsent(tag, k -> new CopyOnWriteArrayList<>()).add(task);
            }
        }
        // execute Observers
        notifyObserver(task, "Added");
        return task;
    }

    public void updateTask(Task task){
        Task existingTask = tasks.getOrDefault(task, null);
        if(existingTask != null){
            synchronized (existingTask){
                existingTask.setStatus(task.getStatus());
                existingTask.setDeadline(task.getDeadline());
                existingTask.setName(task.getName());
                existingTask.setTags(task.getTags());
                existingTask.setStoryPoints(task.getStoryPoints());
                existingTask.setDescription(task.getDescription());
            }
            // execute Observers
            notifyObserver(task, "Updated");
        }
    }

    public void removeTask(Task task){
        Task existingTask = tasks.remove(task);
        if(existingTask != null){
            for(String tag : task.getTags()){
                List<Task> taskList = tagIndex.get(tag);
                if(taskList != null){
                    taskList.remove(task);
                    if (taskList.isEmpty()) {
                        tagIndex.remove(tag);
                    }
                    // execute Observers
                    notifyObserver(task, "Deleted");
                }
            }
        }
    }

    private void notifyObserver(Task task, String message){
        for(TaskAction observer : taskActions){
            observer.execute(task, message);
        }
    }

    public void getAllTasks(){
        System.out.println("All Tasks details");
        for(Task task : tasks.values()){
            System.out.println("Task Name: " + task.getName());
            System.out.println("Task Description: " + task.getDescription());
            System.out.println("Task Deadline: " + task.getDeadline());
            System.out.println("Task Story Points: " + task.getDeadline());
            System.out.println();
        }
        System.out.println();
    }

    public Map<String, Task> getTasks() {
        return tasks;
    }

    public Map<String, List<Task>> getTagIndex() {
        return tagIndex;
    }

    public List<TaskAction> getTaskActions() {
        return taskActions;
    }
}
