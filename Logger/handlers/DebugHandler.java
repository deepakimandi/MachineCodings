package org.example.Logger.handlers;

import org.example.Logger.enums.LogLevel;

public class DebugHandler extends LogHandler{
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.DEBUG;
    }

}
