package dnar;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks, providing operations to add, delete, and retrieve tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list and saves the updated task list to storage.
     *
     * @param task     The task to be added.
     * @param storage  The storage to save the updated task list.
     */
    public void addTask(Task task, Storage storage) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    /**
     * Deletes a task at the specified index and saves the updated task list to storage.
     *
     * @param index    The index of the task to be deleted.
     * @param storage  The storage to save the updated task list.
     * @return The deleted task.
     */
    public Task deleteTask(int index, Storage storage) {
        Task removedTask = tasks.remove(index);
        storage.saveTasks(tasks);
        return removedTask;
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Validates whether the given index is within the valid range of task indices.
     *
     * @param index The index to validate.
     * @throws DNarException If the index is out of bounds.
     */
    public void validateIndex(int index) throws DNarException {
        if (index < 0 || index >= tasks.size()) { // Changed to 0-based index
            throw new DNarException("This does not exist!! Try 1 to " + tasks.size() + " instead:D");
        }
    }
}
