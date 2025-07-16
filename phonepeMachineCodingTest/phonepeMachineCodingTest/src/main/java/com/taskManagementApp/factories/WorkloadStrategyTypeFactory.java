package main.java.com.taskManagementApp.factories;

import main.java.com.taskManagementApp.enums.WorkloadStrategyType;
import main.java.com.taskManagementApp.strategies.DefaultWorkloadStrategy;
import main.java.com.taskManagementApp.strategies.WorkloadStrategy;

public class WorkloadStrategyTypeFactory {
    public static WorkloadStrategy selectWorkloadStrategyType(WorkloadStrategyType workloadStrategyType) {
        switch (workloadStrategyType) {
            case DEFAULT_WORKLOAD_STRATEGY:
                return new DefaultWorkloadStrategy();
            default:
                throw new IllegalArgumentException("Invalid Task Type");
        }
    }
}
