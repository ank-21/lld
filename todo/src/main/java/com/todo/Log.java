package main.java.com.todo;

import java.time.LocalDateTime;

public class Log {
    private String message;
    private Task task;
    private LocalDateTime logTime;

    public Log(String message, Task task) {
        this.message = message;
        this.task = task;
        logTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }
}
