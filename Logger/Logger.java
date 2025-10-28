package org.example.Logger;

import org.example.Logger.handlers.LogHandler;
import org.example.Logger.enums.LogLevel;
import org.example.Logger.model.LogMessage;

public class Logger {

    private static final Logger INSTANCE = new Logger();

    private final LogHandler handlerChain;

    private Logger() {
        handlerChain = LogHandlerConfiguration.build(); // assuming this method sets up the chain
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void log(LogLevel level, String message) {
        LogMessage msg = new LogMessage(level, message, System.currentTimeMillis());
        handlerChain.handle(msg);
    }

    public void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    public void info(String msg) {
        log(LogLevel.INFO, msg);
    }

    public void warn(String msg) {
        log(LogLevel.WARN, msg);
    }

    public void error(String msg) {
        log(LogLevel.ERROR, msg);
    }

    public void fatal(String msg) {
        log(LogLevel.FATAL, msg);
    }
}
