package main.java.com.todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Task {
    private String id;
    private String name;
    private String description;
    private List<String> tags;
    private LocalDateTime deadline;
    private double storyPoints;
    private Status status;
    private LocalDateTime createdDate;

    public Task(String name, String description, List<String> tags, LocalDateTime deadline, double storyPoints) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.deadline = deadline;
        this.storyPoints = storyPoints;
        this.status = Status.TODO;
        this.createdDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public double getStoryPoints() {
        return storyPoints;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setStoryPoints(double storyPoints) {
        this.storyPoints = storyPoints;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
