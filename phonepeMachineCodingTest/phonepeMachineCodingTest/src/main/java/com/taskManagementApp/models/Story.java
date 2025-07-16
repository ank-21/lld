package main.java.com.taskManagementApp.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Story extends TaskEntity {
    private final List<Task> tasks;

    public Story(String title, String description, LocalDate deadline, User assignedUser) {
        super(title, description, deadline, assignedUser);
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

