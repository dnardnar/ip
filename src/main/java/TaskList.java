import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task, Storage storage) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    public Task deleteTask(int index, Storage storage) {
        Task removedTask = tasks.remove(index);
        storage.saveTasks(tasks);
        return removedTask;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void validateIndex(int index) throws DNarException {
        if (index < 0 || index >= tasks.size()) { // Changed to 0-based index
            throw new DNarException("This does not exist!! Try 1 to " + tasks.size() + " instead:D");
        }
    }
}
