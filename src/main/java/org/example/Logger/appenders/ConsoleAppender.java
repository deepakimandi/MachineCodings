package org.example.Logger.appenders;

import lombok.RequiredArgsConstructor;
import org.example.Logger.formatters.LogFormatter;
import org.example.Logger.model.LogMessage;

@RequiredArgsConstructor
public class ConsoleAppender implements LogAppender {

    private final LogFormatter formatter;

    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }
}
