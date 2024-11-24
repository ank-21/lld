package main.java.com.todo;

import java.time.LocalDateTime;
import java.util.List;

public class TaskStatisticsManager {
    private static TaskStatisticsManager instance;


    private TaskStatisticsManager(){}

    public static TaskStatisticsManager getInstance(){
        if(instance == null){
            instance = new TaskStatisticsManager();
        }
        return instance;
    }

    public void getStatistics(LocalDateTime startTime, LocalDateTime endTime){
        int addedTasks = 0;
        int completedTasks = 0;
        int spilledTasks = 0;

        for (Task task : TaskManager.getInstance().getTasks().values()) {
            LocalDateTime createdDate = task.getCreatedDate();
            if (createdDate.isAfter(startTime) && createdDate.isBefore(endTime)) {
                addedTasks++;
            }
            if (task.getStatus() == Status.DONE) {
                completedTasks++;
            }
            if (task.getDeadline().isBefore(LocalDateTime.now()) && task.getStatus() != Status.DONE) {
                spilledTasks++;
            }
        }
        System.out.println("Statistics from " + startTime + " to " + endTime + ":");
        System.out.println("Tasks Added: " + addedTasks);
        System.out.println("Tasks Completed: " + completedTasks);
        System.out.println("Tasks Spilled Over: " + spilledTasks);
    }

    public void getActivityLog(Task task, LocalDateTime startTime, LocalDateTime endTime){
        List<Log> logs = LogAction.getInstance().getLogsForTask(task, startTime, endTime);
        System.out.println("Activity Log for " + task.getName());
        for(Log log : logs){
            System.out.println("Time: " + log.getLogTime());
            System.out.println("Log Name: " + log.getTask().getName());
            System.out.println("Message: " + log.getMessage());
        }
    }
}
