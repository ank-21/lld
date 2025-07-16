package main.java.com.taskManagementApp.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class User implements Observer{
    private final String userId;
    private final String name;
    private final String email;
    private final List<TaskEntity> tasks;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.tasks = new CopyOnWriteArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public List<TaskEntity> getTasks() {
        return Collections.unmodifiableList(new ArrayList<>(tasks));
    }

    public void addTask(TaskEntity task) {
        // Check for duplicate task
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}
