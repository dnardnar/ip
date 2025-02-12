package dnar;

import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {
    public static void parse(String command, TaskList taskList, UI ui, Storage storage) {
        assert command != null : "Command should not be null";
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";

        try {
            if (command.equals("list")) {
                ui.listTasks(taskList);
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.validateIndex(index);
                assert index >= 0 && index < taskList.size() : "Index should be within bounds";
                taskList.getTask(index).markDone();
                storage.saveTasks(taskList.getTasks());
                ui.showMarkDone(taskList.getTask(index));
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.validateIndex(index);
                assert index >= 0 && index < taskList.size() : "Index should be within bounds";
                taskList.getTask(index).markNotDone();
                storage.saveTasks(taskList.getTasks());
                ui.showUnmarkDone(taskList.getTask(index));
            } else if (command.startsWith("todo")) {
                String description = command.substring(4).trim();
                if (description.isEmpty()) {
                    throw new DNarException("NOOO!!! The description of a todo cannot be empty.");
                }
                assert !description.isEmpty() : "Description should not be empty";
                Task task = new ToDo(description);
                taskList.addTask(task, storage);
                ui.showAddedTask(task, taskList.size());
            } else if (command.startsWith("deadline")) {
                String[] parts = command.substring(8).split(" /by ", 2);
                if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                    throw new DNarException("NOOO!!! Both description and deadline time cannot be empty.");
                }
                assert parts.length == 2 : "Command should have exactly two parts";
                try {
                    Task task = new Deadline(parts[0].trim(), parts[1].trim());
                    taskList.addTask(task, storage);
                    ui.showAddedTask(task, taskList.size());
                } catch (DateTimeParseException e) {
                    throw new DNarException("Invalid date format! Please use yyyy-MM-dd.");
                }
            } else if (command.startsWith("event")) {
                String[] parts = command.substring(5).split(" /");
                if (parts.length < 3) {
                    throw new DNarException("NOOO!!! All fields for the event must be filled in.");
                }
                assert parts.length >= 3 : "Command should have at least three parts";
                Task task = new Event(parts[0], parts[1].substring(4), parts[2].substring(3));
                taskList.addTask(task, storage);
                ui.showAddedTask(task, taskList.size());
            } else if (command.startsWith("delete")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.validateIndex(index);
                assert index >= 0 && index < taskList.size() : "Index should be within bounds";
                Task removedTask = taskList.deleteTask(index, storage);
                ui.showDeletedTask(removedTask, taskList.size());
            } else if (command.startsWith("find")) {
                String keyword = command.substring(4).trim();
                if (keyword.isEmpty()) {
                    throw new DNarException("NOOO!!! The search keyword cannot be empty.");
                }
                assert !keyword.isEmpty() : "Keyword should not be empty";
                List<Task> matchingTasks = taskList.findTasksByKeyword(keyword);
                if (matchingTasks.isEmpty()) {
                    ui.showError("No tasks found with the keyword: " + keyword);
                } else {
                    ui.showMatchingTasks(matchingTasks);
                }
            } else {
                throw new DNarException("HUHH!!! WDYM :-(");
            }
        } catch (DNarException e) {
            ui.showError(e.getMessage());
        }
    }
}
