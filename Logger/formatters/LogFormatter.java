package org.example.Logger.formatters;

import org.example.Logger.model.LogMessage;

public interface LogFormatter {
    String format(LogMessage message);
}
