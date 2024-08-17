import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerFramework {
    public static void main(String [] args){
        CustomLogger logObject = new InfoLogger(new DebugLogger(new ErrorLogger(null)));
        LogAppender fileAppender = new FileLogAppender("log.txt");
        LogAppender consoleAppender = new ConsoleLogAppender();

        Log log1 = new Log("This is a debug message!", "2024-08-17 17:11:10", LogLevel.DEBUG);
        Log log2 = new Log("x is not defined!", getCurrentTimestamp(), LogLevel.ERROR);
        Log log3 = new Log("x value is increasing!", getCurrentTimestamp(), LogLevel.INFO);
        Log log4 = new Log("x value is equals to y!", getCurrentTimestamp(10), LogLevel.INFO);
        Log log7 = new Log("Code is breaking!", getCurrentTimestamp(20), LogLevel.ERROR);
        Log log6 = new Log("Code is breaking!", getCurrentTimestamp(15), LogLevel.ERROR);
        Log log5 = new Log("Code is breaking!", getCurrentTimestamp(), LogLevel.ERROR);

        logObject.addLog(log1, fileAppender);
        logObject.addLog(log2, consoleAppender);
        logObject.addLog(log3, fileAppender);
        logObject.addLog(log4, consoleAppender);
        logObject.addLog(log5, fileAppender);
        logObject.addLog(log6, fileAppender);
        logObject.addLog(log7, fileAppender);

        // Close the fileAppender explicitly
        ((FileLogAppender) fileAppender).closeAppender();
    }

    public static String getCurrentTimestamp() {
        return getCurrentTimestamp(0);
    }
    public static String getCurrentTimestamp(int subtractMinutes) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().minusMinutes(subtractMinutes).format(dateFormat);
    }
}
