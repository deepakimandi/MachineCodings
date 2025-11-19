package org.example.FlipMed.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    // HH? -> two digits, H -> 0-23 (single or double digits)
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    public static LocalTime convertStringToLocalTime(String timeStr) {
        try {
            return LocalTime.parse(timeStr, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid time format: " + timeStr);
            return null;
        }
    }
}
