package dnar;

/**
 * Represents an event task with a start and end date/time. Events have a description,
 * a start time, and an end time. They inherit common task functionalities from the Task class.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs an Event task with a description, start, and end date/time.
     * This constructor is used to create a new event task.
     *
     * @param description The description of the event.  Should be a concise summary of the event.
     * @param start       The start date/time of the event.  Should be in a user-readable format.
     * @param end         The end date/time of the event. Should be in a user-readable format.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event task with a description, start and end date/time, and completion status.
     * This constructor is used when loading an existing event task from storage.
     *
     * @param description The description of the event.  Should be a concise summary of the event.
     * @param start       The start date/time of the event.  Should be in a user-readable format.
     * @param end         The end date/time of the event. Should be in a user-readable format.
     * @param isDone      Whether the event task is completed. Indicates if the event has already occurred.
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    /**
     * Converts the event into a string format suitable for storage.
     * The format is: "E | isDone | description | start | end".  The start and end times are
     * stored as is, assuming they are in a consistent format.
     *
     * @return A formatted string representing the event for file storage.
     */
    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }

    /**
     * Returns a string representation of the event task for display to the user.
     * Includes the task type, completion status, and event-specific details (start and end times).
     *
     * @return A formatted string showing the event details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
