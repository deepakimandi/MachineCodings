package org.example.Logger.appenders;

import org.example.Logger.formatters.LogFormatter;
import org.example.Logger.model.LogMessage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender {

    private final LogFormatter formatter;
    private final BufferedWriter writer;

    public FileAppender(LogFormatter formatter, String fileName) {
        this.formatter = formatter;
        try {
            this.writer = new BufferedWriter(new FileWriter(fileName, true)); // append = true
        } catch (IOException e) {
            throw new RuntimeException("Failed to open log file", e);
        }
    }

    @Override
    public synchronized void append(LogMessage message) {
        try {
            writer.write(formatter.format(message));
            writer.newLine();
            writer.flush(); // flush can be batched or delayed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
