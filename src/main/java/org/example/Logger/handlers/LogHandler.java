package org.example.Logger.handlers;

import org.example.Logger.enums.LogLevel;
import org.example.Logger.appenders.LogAppender;
import org.example.Logger.model.LogMessage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {

    // Next handler in the chain
    protected LogHandler next;

    // List of subscribed appenders
    protected final List<LogAppender> appenders = new CopyOnWriteArrayList<>();

    // Subscribe a new appender
    public void subscribe(LogAppender observer) {
        appenders.add(observer);
    }

    // Notify all subscribed appenders
    protected void notifyObservers(LogMessage message) {
        for (LogAppender appender : appenders) {
            appender.append(message);
        }
    }

    // Handle a log message
    public void handle(LogMessage message) {
        if (canHandle(message.getLevel())) {
            notifyObservers(message);
        } else if (next != null) {
            next.handle(message);
        }
    }

    // Abstract method for specific log level handling
    protected abstract boolean canHandle(LogLevel level);

    // Set the next handler in the chain
    public void setNext(LogHandler next) {
        this.next = next;
    }
}
