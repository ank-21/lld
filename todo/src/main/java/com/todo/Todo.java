package main.java.com.todo;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Todo {
    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();
        TaskStatisticsManager statisticsManager = TaskStatisticsManager.getInstance();

        // Register observers
        taskManager.registerObserver(LogAction.getInstance());

        // For future purpose
        //taskManager.registerObserver(NotificationManager.getInstance());

        // CREATE TASK
        Task task1 = taskManager.addTask("Solve LLD", "Solve some LLD problems", Arrays.asList("LLD", "Machine Coding", "Low Level Design", "Medium", "Interview"), LocalDateTime.of(2024, 11, 26, 23, 59), 3);
        Task task2 = taskManager.addTask("Solve HLD", "Solve some HLD problems", Arrays.asList("HLD", "High Level Design", "Hard", "Interview"), LocalDateTime.of(2024, 11, 30, 23, 59), 4);
        Task task3 = taskManager.addTask("Solve DSA", "Solve some DSA problems", Arrays.asList("DSA", "DP", "Graph", "Array", "Leetcode", "Medium", "Interview"), LocalDateTime.of(2024, 12, 02, 23, 59), 4);

        // UPDATE TASK
        task1.setStatus(Status.INPROGRESS);
        taskManager.updateTask(task1);

        // READ ALL TASKS
        taskManager.getAllTasks();

        // Mark Test as done
        task3.setStatus(Status.DONE);
        taskManager.updateTask(task3);

        // REMOVE TASK
        taskManager.removeTask(task2);

        // READ ALL TASKS
        taskManager.getAllTasks();

        // Activity LOG
        statisticsManager.getActivityLog(task1, LocalDateTime.of(2024, 11, 22, 00, 00), LocalDateTime.now());

        // Statistics
        statisticsManager.getStatistics(LocalDateTime.of(2024, 11, 23, 00,00), LocalDateTime.of(2024, 12, 02, 00,00));
    }
}
