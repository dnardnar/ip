package dnar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    // Date format used for input (e.g., from user input). Chosen to comply with the 'yyyy-MM-dd' standard.
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Date format used for output (e.g., displaying to the user). Chosen to provide a user-friendly display.
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Parses a date string in the format 'yyyy-MM-dd' into a LocalDate object.
     * @param date The date string to parse.
     * @return The LocalDate object representing the parsed date.
     * @throws DateTimeParseException If the date string cannot be parsed.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, INPUT_FORMAT);
    }

    /**
     * Formats a LocalDate object into a string in the format 'MMM dd yyyy'.
     * @param date The LocalDate object to format.
     * @return The formatted date string.
     */
    public static String formatDate(LocalDate date) {
        return date.format(OUTPUT_FORMAT);
    }

    /**
     * Parses a date string in the format 'MMM dd yyyy' and formats it into 'yyyy-MM-dd'.
     * This is used to convert a date from user-friendly format to storage format
     * @param formattedDate The date string in 'MMM dd yyyy' format to parse and reformat.
     * @return The date string in 'yyyy-MM-dd' format.
     * @throws DateTimeParseException If the date string cannot be parsed.
     */
    public static String reformatDateForStorage(String formattedDate) throws DateTimeParseException {
        try {
            LocalDate date = LocalDate.parse(formattedDate, OUTPUT_FORMAT);
            return date.format(INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Could not reformat date: " + e.getMessage(), formattedDate, e.getErrorIndex());
        }
    }
}
