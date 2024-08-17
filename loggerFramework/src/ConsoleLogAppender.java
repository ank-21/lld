public class ConsoleLogAppender implements LogAppender{
    @Override
    public void logAppend(String message) {
        System.out.println("Logging using console log");
        System.out.println(message);
    }
}
