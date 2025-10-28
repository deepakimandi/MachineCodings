package org.example.Logger;

import org.example.Logger.handlers.*;
import org.example.Logger.appenders.LogAppender;
import org.example.Logger.enums.LogLevel;

public class LogHandlerConfiguration {

    private static final LogHandler debug = new DebugHandler();
    private static final LogHandler info = new InfoHandler();
    private static final LogHandler warn = new WarnHandler();
    private static final LogHandler error = new ErrorHandler();
    private static final LogHandler fatal = new FatalHandler();

    public static LogHandler build() {
        debug.setNext(info);
        info.setNext(warn);
        warn.setNext(error);
        error.setNext(fatal);
        return debug; // head of the chain
    }

    public static void addAppenderForLevel(LogLevel level, LogAppender appender) {
        switch (level) {
            case DEBUG -> debug.subscribe(appender);
            case INFO -> info.subscribe(appender);
            case WARN -> warn.subscribe(appender);
            case ERROR -> error.subscribe(appender);
            case FATAL -> fatal.subscribe(appender);
        }
    }
}
