public class CustomLogger {
    CustomLogger nextLogger;

    CustomLogger(CustomLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    void addLog(Log log, LogAppender logAppender){
        if(nextLogger != null){
            nextLogger.addLog(log, logAppender);
        }
    }
}
