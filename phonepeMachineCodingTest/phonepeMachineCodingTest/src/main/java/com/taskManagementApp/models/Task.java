package main.java.com.taskManagementApp.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task extends TaskEntity {
    private final List<Subtask> subtasks;

    public Task(String title, String description, LocalDate deadline, User assignedUser) {
        super(title, description, deadline, assignedUser);
        this.subtasks = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }



    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}

