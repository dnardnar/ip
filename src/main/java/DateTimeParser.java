import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, INPUT_FORMAT);
    }

    public static String formatDate(LocalDate date) {
        return date.format(OUTPUT_FORMAT);
    }
}