package main.java.com.taskManagementApp.strategies;

import main.java.com.taskManagementApp.enums.TaskStatus;
import main.java.com.taskManagementApp.models.User;

import java.util.Map;

public interface WorkloadStrategy {
    Map<TaskStatus, Integer> calculateWorkload(User user);
}
