package dnar;

import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    private static final String ERROR_EMPTY_TODO_DESCRIPTION = "NOOO!!! The description of a todo cannot be empty.";
    private static final String ERROR_EMPTY_DEADLINE_FIELDS = "NOOO!!! Both description and deadline time cannot be empty.";
    private static final String ERROR_INVALID_DATE_FORMAT = "Invalid date format! Please use yyyy-MM-dd.";
    private static final String ERROR_MISSING_EVENT_FIELDS = "NOOO!!! All fields for the event must be filled in.";
    private static final String ERROR_EMPTY_KEYWORD = "NOOO!!! The search keyword cannot be empty.";
    private static final String ERROR_INVALID_COMMAND = "HUHH!!! WDYM :-(";

    /**
     * Parses the given command and performs the corresponding action.
     *
     * @param command  The command to parse.
     * @param taskList The list of tasks.
     * @param ui       The user interface.
     * @param storage  The storage object.
     */
    public static void parse(String command, TaskList taskList, UI ui, Storage storage) {
        try {
            switch (command) {
            case COMMAND_LIST:
                listTasks(taskList, ui);
                break;
            default:
                if (command.startsWith(COMMAND_MARK)) {
                    markTask(command, taskList, ui, storage, true);
                } else if (command.startsWith(COMMAND_UNMARK)) {
                    markTask(command, taskList, ui, storage, false);
                } else if (command.startsWith(COMMAND_TODO)) {
                    addTodo(command, taskList, ui, storage);
                } else if (command.startsWith(COMMAND_DEADLINE)) {
                    addDeadline(command, taskList, ui, storage);
                } else if (command.startsWith(COMMAND_EVENT)) {
                    addEvent(command, taskList, ui, storage);
                } else if (command.startsWith(COMMAND_DELETE)) {
                    deleteTask(command, taskList, ui, storage);
                } else if (command.startsWith(COMMAND_FIND)) {
                    findTasks(command, taskList, ui);
                } else {
                    throw new DNarException(ERROR_INVALID_COMMAND);
                }
            }
        } catch (DNarException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void listTasks(TaskList taskList, UI ui) {
        ui.listTasks(taskList);
    }

    private static void markTask(String command, TaskList taskList, UI ui, Storage storage, boolean isDone) throws DNarException {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            taskList.validateIndex(index);
            if (isDone) {
                taskList.getTask(index).markDone();
                ui.showMarkDone(taskList.getTask(index));
            } else {
                taskList.getTask(index).markNotDone();
                ui.showUnmarkDone(taskList.getTask(index));
            }
            storage.saveTasks(taskList.getTasks());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DNarException("Invalid mark/unmark command format. Please provide a valid index.");
        }
    }

    private static void addTodo(String command, TaskList taskList, UI ui, Storage storage) throws DNarException {
        String description = command.substring(COMMAND_TODO.length()).trim();
        if (description.isEmpty()) {
            throw new DNarException(ERROR_EMPTY_TODO_DESCRIPTION);
        }
        Task task = new ToDo(description);
        taskList.addTask(task, storage);
        ui.showAddedTask(task, taskList.size());
    }

    private static void addDeadline(String command, TaskList taskList, UI ui, Storage storage) throws DNarException {
        String[] parts = command.substring(COMMAND_DEADLINE.length()).split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DNarException(ERROR_EMPTY_DEADLINE_FIELDS);
        }
        try {
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
            taskList.addTask(task, storage);
            ui.showAddedTask(task, taskList.size());
        } catch (DateTimeParseException e) {
            throw new DNarException(ERROR_INVALID_DATE_FORMAT);
        }
    }

    private static void addEvent(String command, TaskList taskList, UI ui, Storage storage) throws DNarException {
        String[] parts = command.substring(COMMAND_EVENT.length()).split(" /");
        if (parts.length < 3) {
            throw new DNarException(ERROR_MISSING_EVENT_FIELDS);
        }
        Task task = new Event(parts[0], parts[1].substring(4), parts[2].substring(3));
        taskList.addTask(task, storage);
        ui.showAddedTask(task, taskList.size());
    }

    private static void deleteTask(String command, TaskList taskList, UI ui, Storage storage) throws DNarException {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task removedTask = taskList.deleteTask(index, storage);
            ui.showDeletedTask(removedTask, taskList.size());
        }  catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DNarException("Invalid delete command format. Please provide a valid index.");
        }
    }

    private static void findTasks(String command, TaskList taskList, UI ui) throws DNarException {
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
}
