package main.java.com.taskManagementApp.strategies;

import main.java.com.taskManagementApp.enums.TaskStatus;
import main.java.com.taskManagementApp.models.TaskEntity;
import main.java.com.taskManagementApp.models.User;

import java.util.EnumMap;
import java.util.Map;

public class DefaultWorkloadStrategy implements WorkloadStrategy{
    @Override
    public Map<TaskStatus, Integer> calculateWorkload(User user) {
        Map<TaskStatus, Integer> workload = new EnumMap<>(TaskStatus.class);
        for (TaskStatus status : TaskStatus.values()) {
            workload.put(status, 0);
        }
        for (TaskEntity task : user.getTasks()) {
            workload.put(task.getStatus(), workload.get(task.getStatus()) + 1);
        }
        return workload;
    }
}
