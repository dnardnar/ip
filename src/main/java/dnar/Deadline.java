package dnar;

import java.time.LocalDate;

/**
 * Represents a task with a specific deadline.
 * Deadlines have a description and an end date, and can be marked as done.
 */
public class Deadline extends Task {
    private LocalDate end;

    /**
     * Constructs a Deadline task with a description and an end date.
     * This constructor is typically used when creating a new deadline task
     * from user input.
     *
     * @param description The description of the deadline task.
     * @param end         The deadline date in string format (yyyy-MM-dd). It is assumed to be a valid date format.
     */
    public Deadline(String description, String end) {
        super(description);
        this.end = DateTimeParser.parseDate(end);
    }

    /**
     * Constructs a Deadline task with a description, an end date, and a completion status.
     * This constructor is typically used when loading a deadline task from storage,
     * where the completion status is already known.
     *
     * @param description The description of the deadline task.
     * @param end         The deadline date in string format (yyyy-MM-dd). It is assumed to be a valid date format.
     * @param isDone      Whether the task is marked as done.
     */
    public Deadline(String description, String end, boolean isDone) {
        super(description, isDone);
        this.end = DateTimeParser.parseDate(end);
    }

    public void setEnd(String end) {
        this.end = DateTimeParser.parseDate(end);
    }
    /**
     * Converts the Deadline task to a data string for file storage.
     * The format is: "D | isDone | description | end".  The 'end' date is
     * formatted using DateTimeParser for consistent storage and retrieval.
     *
     * @return A formatted string representation of the deadline task for storage.
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + DateTimeParser.formatDate(end);
    }

    /**
     * Returns a string representation of the deadline task, including the task type,
     * status, and deadline. The deadline is formatted for user-friendly display.
     *
     * @return A formatted string showing the task type, status, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDate(end) + ")";
    }
}
