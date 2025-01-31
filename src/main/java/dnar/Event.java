package dnar;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Constructs an Event task with a description, start, and end date/time.
     *
     * @param description The description of the event.
     * @param start The start date/time of the event.
     * @param end The end date/time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event task with a description, start and end date/time, and completion status.
     *
     * @param description The description of the event.
     * @param start The start date/time of the event.
     * @param end The end date/time of the event.
     * @param isDone Whether the event task is completed.
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Converts the event into a string format suitable for storage.
     *
     * @return A formatted string representing the event for file storage.
     */
    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string showing the event details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
