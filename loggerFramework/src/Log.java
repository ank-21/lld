import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    String message;
    LocalDateTime timestamp;
    LogLevel level;

    public Log(String message, String timestamp, LogLevel level){
        this.message = message;
        this.level = level;
        addTimestamp(timestamp);
    }
    private void addTimestamp(String timestamp) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.parse(timestamp, dateFormat);
    }
}
