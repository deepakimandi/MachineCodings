package org.example.Logger.handlers;

import org.example.Logger.enums.LogLevel;

public class ErrorHandler extends LogHandler{
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }

}
