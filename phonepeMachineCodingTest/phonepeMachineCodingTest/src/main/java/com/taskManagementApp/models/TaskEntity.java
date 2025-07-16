package main.java.com.taskManagementApp.models;

import main.java.com.taskManagementApp.enums.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public abstract class TaskEntity {
    protected String id;
    protected String title;
    protected String description;
    protected LocalDate deadline;
    protected User assignedUser;
    protected TaskStatus status;

    public TaskEntity(String title, String description, LocalDate deadline, User assignedUser) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.assignedUser = assignedUser;
        this.status = TaskStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}
