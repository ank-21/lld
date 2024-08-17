public class DebugLogger extends CustomLogger {

    DebugLogger(CustomLogger nextLogger){
        super(nextLogger);
    }

    @Override
    void addLog(Log log, LogAppender logAppender){
        if(LogLevel.DEBUG == log.level){
            String logMessage = log.timestamp + " " + log.message;
            logAppender.logAppend(logMessage);
        }else{
            super.addLog(log, logAppender);
        }
    }
}
