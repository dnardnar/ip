import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate end;

    public Deadline(String description, String end) {
        super(description);
        this.end = DateTimeParser.parseDate(end);
    }

    public Deadline(String description, String end, boolean isDone) {
        super(description, isDone);
        this.end = DateTimeParser.parseDate(end);
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + DateTimeParser.formatDate(end);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDate(end) + ")";
    }
}
