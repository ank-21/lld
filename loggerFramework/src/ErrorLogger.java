public class ErrorLogger extends CustomLogger {

    ErrorLogger(CustomLogger nextLogger){
        super(nextLogger);
    }

    @Override
    void addLog(Log log, LogAppender logAppender){
        if(LogLevel.ERROR == log.level){
            String logMessage = log.timestamp + " " + log.message;
            logAppender.logAppend(logMessage);
        }else{
            super.addLog(log, logAppender);
        }
    }
}
