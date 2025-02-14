package dnar;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles parsing of user commands and delegates actions to the appropriate components.
 */
public class Parser {

    // Command constants
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_EDIT = "edit";

    // Error message constants
    private static final String ERROR_EMPTY_TODO_DESCRIPTION = "The description of a todo cannot be empty.";
    private static final String ERROR_EMPTY_DEADLINE_FIELDS = "Both description and deadline time cannot be empty.";
    private static final String ERROR_INVALID_DATE_FORMAT = "Invalid date format! Please use yyyy-MM-dd.";
    private static final String ERROR_MISSING_EVENT_FIELDS = "All fields for the event must be filled in.";
    private static final String ERROR_EMPTY_KEYWORD = "The search keyword cannot be empty.";
    private static final String ERROR_INVALID_COMMAND = "Invalid command. Please try again.";
    private static final String ERROR_INVALID_EDIT_FORMAT =
            "Invalid edit command format. Use: edit <index> <field> <new value>";

    /**
     * Parses the given command and performs the corresponding action.
     *
     * @param command  The command to parse.
     * @param taskList The list of tasks.
     * @param ui       The user interface.
     * @param storage  The storage object.
     */
    public static void parse(String command, TaskList taskList, UI ui, Storage storage) {
        assert command != null : "Command should not be null";
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        try {
            if (command.equals(COMMAND_LIST)) {
                listTasks(taskList, ui);
            } else if (command.startsWith(COMMAND_MARK)) {
                handleMarkTask(command, taskList, ui, storage, true);
            } else if (command.startsWith(COMMAND_UNMARK)) {
                handleMarkTask(command, taskList, ui, storage, false);
            } else if (command.startsWith(COMMAND_TODO)) {
                handleAddTodo(command, taskList, ui);
            } else if (command.startsWith(COMMAND_DEADLINE)) {
                handleAddDeadline(command, taskList, ui);
            } else if (command.startsWith(COMMAND_EVENT)) {
                handleAddEvent(command, taskList, ui);
            } else if (command.startsWith(COMMAND_DELETE)) {
                handleDeleteTask(command, taskList, ui);
            } else if (command.startsWith(COMMAND_FIND)) {
                handleFindTasks(command, taskList, ui);
            } else if (command.startsWith(COMMAND_EDIT)) { // New edit functionality
                handleEditTask(command, taskList, ui);
            } else {
                throw new DNarException(ERROR_INVALID_COMMAND);
            }
        } catch (DNarException e) {
            ui.showError(e.getMessage());
        }
    }

    // Handles listing tasks
    private static void listTasks(TaskList taskList, UI ui) {
        ui.listTasks(taskList);
    }

    // Handles marking/unmarking tasks
    private static void handleMarkTask(String command, TaskList taskList, UI ui, Storage storage, boolean isDone)
            throws DNarException {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            taskList.validateIndex(index);

            Task task = taskList.getTask(index);
            if (isDone) {
                task.markDone();
                ui.showMarkDone(task);
            } else {
                task.markNotDone();
                ui.showUnmarkDone(task);
            }

            storage.saveTasks(taskList.getTasks());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DNarException("Invalid mark/unmark command format. Please provide a valid index.");
        }
    }

    // Handles adding a ToDo task
    private static void handleAddTodo(String command, TaskList taskList, UI ui) throws DNarException {
        String description = command.substring(COMMAND_TODO.length()).trim();
        if (description.isEmpty()) {
            throw new DNarException(ERROR_EMPTY_TODO_DESCRIPTION);
        }

        Task task = new ToDo(description);
        taskList.addTask(task);
        ui.showAddedTask(task, taskList.size());
    }

    // Handles adding a Deadline task
    private static void handleAddDeadline(String command, TaskList taskList, UI ui)
            throws DNarException {
        String[] parts = command.substring(COMMAND_DEADLINE.length()).split(" /by ", 2);

        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DNarException(ERROR_EMPTY_DEADLINE_FIELDS);
        }

        try {
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
            taskList.addTask(task);
            ui.showAddedTask(task, taskList.size());
        } catch (DateTimeParseException e) {
            throw new DNarException(ERROR_INVALID_DATE_FORMAT);
        }
    }

    // Handles adding an Event
    private static void handleAddEvent(String command, TaskList taskList, UI ui) throws DNarException {
        String[] parts = command.substring(COMMAND_EVENT.length()).split(" /");
        if (parts.length < 3) {
            throw new DNarException(ERROR_MISSING_EVENT_FIELDS);
        }
        Task task = new Event(parts[0], parts[1].substring(4), parts[2].substring(3));
        taskList.addTask(task);
        ui.showAddedTask(task, taskList.size());
    }

    // Handles deleting a task
    private static void handleDeleteTask(String command, TaskList taskList, UI ui)
            throws DNarException {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task removedTask = taskList.deleteTask(index);
            ui.showDeletedTask(removedTask, taskList.size());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DNarException("Invalid delete command format. Please provide a valid index.");
        }
    }

    // Handles finding tasks by keyword
    private static void handleFindTasks(String command, TaskList taskList, UI ui) throws DNarException {
        String keyword = command.substring(COMMAND_FIND.length()).trim();
        if (keyword.isEmpty()) {
            throw new DNarException(ERROR_EMPTY_KEYWORD);
        }
        List<Task> matchingTasks = taskList.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showError("No tasks found with the keyword: " + keyword);
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }

    // Handles editing a specific field of a task
    private static void handleEditTask(String command, TaskList taskList, UI ui) throws DNarException {
        try {
            String[] parts = command.split(" ", 4); // e.g., edit 2 description New Description
            int index = Integer.parseInt(parts[1]) - 1;
            String field = parts[2].trim();
            String newValue = parts[3].trim();

            // Edit the specified field of the selected task
            taskList.editTask(index, field.toLowerCase(), newValue);

            // Show success message
            Task updatedTask = taskList.getTask(index);
            ui.showEditSuccess(updatedTask);

        } catch (DateTimeParseException e) {
            throw new DNarException(ERROR_INVALID_DATE_FORMAT);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DNarException(ERROR_INVALID_EDIT_FORMAT);
        }
    }
}
