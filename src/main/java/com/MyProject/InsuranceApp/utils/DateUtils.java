package com.MyProject.InsuranceApp.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateUtils {

    // Prevent instantiation
    private DateUtils() {}

    // Define your default format pattern
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    /**
     * Converts a date string like "14-May-1988" to LocalDate.
     */
    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
    }

    /**
     * Converts a LocalDate to a string like "14-May-1988".
     */
    public static String formatDate(LocalDate date) {
        if (date == null) return null;
        return date.format(FORMATTER);
    }

    /**
     * Converts one date string to the target "dd-MMM-yyyy" format.
     * Example: "1988-05-14" → "14-May-1988"
     */
    public static String convertToDefaultFormat(String dateStr, String inputPattern) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
            LocalDate date = LocalDate.parse(dateStr, inputFormatter);
            return date.format(FORMATTER);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid conversion from " + inputPattern + " to dd-MMM-yyyy");
        }
    }
}
