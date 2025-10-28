package org.example.Logger.appenders;

import org.example.Logger.model.LogMessage;

public interface LogAppender {
    void append(LogMessage message);
}
