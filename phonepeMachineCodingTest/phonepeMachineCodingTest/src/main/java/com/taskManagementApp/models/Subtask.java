package main.java.com.taskManagementApp.models;

import java.time.LocalDate;

public class Subtask extends TaskEntity {
    private Task parentTask;
    public Subtask(String title, String description, LocalDate deadline, User assignedUser) {
        super(title, description, deadline, assignedUser);
    }
}
