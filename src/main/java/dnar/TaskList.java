package dnar;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a dynamic list of tasks, providing operations to add, delete, retrieve,
 * and search tasks. This list ensures that all operations are properly
 * synchronized with persistent storage.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;
    private static final String ERROR_INVALID_INDEX = "This does not exist!! Try 1 to %d instead:D";

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Constructs a TaskList with a given list of tasks. This constructor is
     * typically used when loading tasks from storage.
     *
     * @param tasks The list of tasks to initialize the TaskList with. Assumes
     *              that the tasks are valid and properly formatted.
     */
    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Adds a task to the list and saves the updated task list to storage.
     * This method appends the task to the end of the list and persists the
     * changes to the storage medium.
     *
     * @param task The task to be added. Must not be null.
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    /**
     * Deletes a task at the specified index and saves the updated task list to storage.
     * This method removes the task at the given index, shifting subsequent tasks
     * to fill the gap, and persists the changes to the storage medium.
     *
     * @param index The index of the task to be deleted. Must be a valid index within
     *              the bounds of the task list.
     * @return The deleted task.
     * @throws DNarException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws DNarException {
        validateIndex(index);
        Task removedTask = tasks.remove(index);
        saveTasks();
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
            throw new DNarException(String.format(ERROR_INVALID_INDEX, tasks.size()));
        }
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param keyword The keyword to find.
     * @return The tasks that contain the keyword.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    private void saveTasks() {
        storage.saveTasks(tasks);
    }
}
