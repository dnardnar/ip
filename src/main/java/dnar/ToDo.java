package dnar;

/**
 * Represents a ToDo task, which is a simple task without a specific date.
 */
class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo task with the given description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone      Whether the task is marked as done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a formatted string representation of the ToDo task for data storage.
     *
     * @return A string formatted as "T | {status} | {description}".
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task, including its type and status.
     *
     * @return A string formatted as "[T]{task description}".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
