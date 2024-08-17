public class InfoLogger extends CustomLogger {
    CustomLogger nextLogger;

    InfoLogger(CustomLogger nextLogger){
        super(nextLogger);
    }

    @Override
    void addLog(Log log, LogAppender logAppender){
        if(LogLevel.INFO == log.level){
            String logMessage = log.timestamp + " " + log.message;
            logAppender.logAppend(logMessage);
        }else{
            super.addLog(log, logAppender);
        }
    }
}
