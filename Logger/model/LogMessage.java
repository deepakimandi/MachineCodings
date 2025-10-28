package org.example.Logger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Logger.enums.LogLevel;

@AllArgsConstructor
@Getter
public class LogMessage {
    private LogLevel level;
    private String message;
    private long timestamp;
}