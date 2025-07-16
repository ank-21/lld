package main.java.com.taskManagementApp;

import main.java.com.taskManagementApp.enums.TaskStatus;
import main.java.com.taskManagementApp.enums.WorkloadStrategyType;
import main.java.com.taskManagementApp.managers.TaskManager;
import main.java.com.taskManagementApp.managers.UserManager;
import main.java.com.taskManagementApp.models.*;
import main.java.com.taskManagementApp.notifications.ConsoleObserverImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = UserManager.getInstance();
        userManager.addUser("ank-21", "Ankit", "ankit@gmail.com");
        userManager.addUser("hari-18", "Hari", "hari@example.com");
        // Will show error for duplicate entry
        // userManager.addUser("ank-21", "Ankit", "ankit@gmail.com");

        // Get users
        User ankit = userManager.getUser("ank-21");
        User hari = userManager.getUser("hari-18");

        TaskManager taskManager = TaskManager.getInstance(WorkloadStrategyType.DEFAULT_WORKLOAD_STRATEGY);

        // add observers
        taskManager.addObserver(new ConsoleObserverImpl());
        // Create a task
        Task softwareTask = taskManager.createTask(
                "Implement Feature X",
                "Develop new feature for the application",
                LocalDate.now().plusDays(30),
                ankit
        );

        // Create a subtask
        Subtask softwareSubTask = taskManager.createSubtask(
                softwareTask.getId(),
                "Design Database Schema",
                "Create database design for new feature",
                LocalDate.now().plusDays(10),
                hari
        );

        // Create tasks for a story
        List<Task> storyTasks = new ArrayList<>();
        Task task1 = taskManager.createTask(
                "Frontend Implementation",
                "Create UI for new module",
                LocalDate.now().plusDays(2),
                hari
        );
        Task task2 = taskManager.createTask(
                "Backend Implementation",
                "Implement backend logic",
                LocalDate.now().plusDays(4),
                ankit
        );
        storyTasks.add(task1);
        storyTasks.add(task2);

        // Create a story
        Story story1 = taskManager.createStory(
                "User Management Module",
                "Implement complete user management",
                LocalDate.now().plusDays(6),
                ankit,
                storyTasks
        );

        // To update Tasks
        TaskUpdateFieldCriteria updateFieldCriteria = new TaskUpdateFieldCriteria();

        updateFieldCriteria.setStatus(TaskStatus.IN_PROGRESS);
        updateFieldCriteria.setDeadline(LocalDate.now().plusDays(3));

        // Update a task
        taskManager.updateTask(
                softwareTask.getId(),
                updateFieldCriteria
        );

        // Move a task
        taskManager.moveTask(softwareSubTask.getId(), task1.getId());

        // Get user workload
        System.out.println("Ankit's Workload: " + taskManager.getUserWorkload(ankit));
    }
}
