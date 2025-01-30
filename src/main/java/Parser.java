import java.time.format.DateTimeParseException;

public class Parser {
    public static void parse(String command, TaskList taskList, UI ui, Storage storage) {
        try {
            if (command.equals("bye")) {
                ui.exit();
            } else if (command.equals("list")) {
                ui.listTasks(taskList);
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.validateIndex(index);
                taskList.getTask(index).markDone();
                storage.saveTasks(taskList.getTasks());
                ui.showMarkDone(taskList.getTask(index));
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.validateIndex(index);
                taskList.getTask(index).markNotDone();
                storage.saveTasks(taskList.getTasks());
                ui.showUnmarkDone(taskList.getTask(index));
            } else if (command.startsWith("todo")) {
                String description = command.substring(4).trim();
                if (description.isEmpty()) {
                    throw new DNarException("NOOO!!! The description of a todo cannot be empty.");
                }
                Task task = new ToDo(description);
                taskList.addTask(task, storage);
                ui.showAddedTask(task, taskList.size());
            } else if (command.startsWith("deadline")) {
                String[] parts = command.substring(8).split(" /by ", 2);
                if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                    throw new DNarException("NOOO!!! Both description and deadline time cannot be empty.");
                }
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
                Task task = new Event(parts[0], parts[1].substring(4), parts[2].substring(3));
                taskList.addTask(task, storage);
                ui.showAddedTask(task, taskList.size());
            } else if (command.startsWith("delete")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                Task removedTask = taskList.deleteTask(index, storage);
                ui.showDeletedTask(removedTask, taskList.size());
            } else {
                throw new DNarException("HUHH!!! WDYM :-(");
            }
        } catch (DNarException e) {
            ui.showError(e.getMessage());
        }
    }
}
