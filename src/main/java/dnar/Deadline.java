package dnar;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate end;

    /**
     * Constructs a Deadline task with a description and an end date.
     *
     * @param description The description of the deadline task.
     * @param end         The deadline date in string format.
     */
    public Deadline(String description, String end) {
        super(description);
        this.end = DateTimeParser.parseDate(end);
    }

    /**
     * Constructs a Deadline task with a description, an end date, and a completion status.
     *
     * @param description The description of the deadline task.
     * @param end         The deadline date in string format.
     * @param isDone      Whether the task is marked as done.
     */
    public Deadline(String description, String end, boolean isDone) {
        super(description, isDone);
        this.end = DateTimeParser.parseDate(end);
    }

    /**
     * Converts the Deadline task to a data string for file storage.
     *
     * @return A formatted string representation of the deadline task for storage.
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + DateTimeParser.formatDate(end);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string showing the task type, status, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDate(end) + ")";
    }
}
