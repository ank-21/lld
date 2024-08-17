import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class FileLogAppender implements LogAppender{
    private String logFile;
    private PrintWriter writer;

    public FileLogAppender(String fileName){
        this.logFile = fileName;
        try {
            FileWriter fileWriter = new FileWriter(logFile, true); // 'true' for appending to the file
            writer = new PrintWriter(fileWriter);
        } catch (IOException e) {
            System.out.println("An error occurred while initializing the FileWriter: " + e.getMessage());
        }
    }
    @Override
    public void logAppend(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        }
    }

    public void closeAppender() {
        if (writer != null) {
            System.out.println("Log is added in the file : " + logFile);
            writer.close();
        }
    }
}
