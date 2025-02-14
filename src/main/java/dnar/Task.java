package dnar;

/**
 * Represents an abstract task with a description and completion status. This class
 * serves as a base for different types of tasks and provides common functionality
 * for marking tasks as done or not done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with a description. The task is initially not marked as done.
     *
     * @param description The description of the task. Must not be null or empty.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with a description and completion status.
     *
     * @param description The description of the task. Must not be null or empty.
     * @param isDone      Whether the task is marked as done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the status icon indicating whether the task is done.
     *
     * @return "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a string representation of the task for storage. This method must be
     * implemented by subclasses to provide the task-specific data in a consistent
     * format.
     *
     * @return A formatted string representing the task for storage.
     */
    public abstract String toDataString();

    /**
     * Returns a string representation of the task for display to the user.
     * Includes the status icon and description.
     *
     * @return A formatted string showing the task details.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
