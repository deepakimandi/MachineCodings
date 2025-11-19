package org.example.Logger;

import org.example.Logger.appenders.ConsoleAppender;
import org.example.Logger.appenders.FileAppender;
import org.example.Logger.enums.LogLevel;
import org.example.Logger.formatters.PlainTextFormatter;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        LogHandlerConfiguration.addAppenderForLevel(
                LogLevel.INFO,
                new ConsoleAppender(new PlainTextFormatter())
        );

        LogHandlerConfiguration.addAppenderForLevel(
                LogLevel.ERROR,
                new ConsoleAppender(new PlainTextFormatter())
        );

        LogHandlerConfiguration.addAppenderForLevel(
                LogLevel.ERROR,
                new FileAppender(new PlainTextFormatter(), "logs.txt")
        );

        // Usage
        logger.info("This is some key information");     // → Console
        logger.error("Oh no! There’s an error");         // → Console + File
    }
}
