package main.java.com.todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LogAction implements TaskAction{
    private static LogAction instance;
    private Map<String, List<Log>> taskLogs;
    private LogAction(){
        taskLogs = new ConcurrentHashMap<>();
    }

    public static LogAction getInstance(){
        if(instance == null){
            instance = new LogAction();
        }
        return instance;
    }
    @Override
    public void execute(Task task, String message) {
        taskLogs.computeIfAbsent(task.getId(), k -> new CopyOnWriteArrayList<>()).add(new Log(message, task));
    }

    public List<Log> getLogsForTask(Task task, LocalDateTime startTime, LocalDateTime endTime){
        List<Log> logs = taskLogs.get(task.getId());
        List<Log> filteredLogs = new CopyOnWriteArrayList<>();
        if(logs != null){
            for(Log log : logs){
                if(log.getLogTime().isAfter(startTime) && log.getLogTime().isBefore(endTime)){
                    filteredLogs.add(log);
                }
            }
        }
        return filteredLogs;
    }
}
