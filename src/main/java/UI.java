import java.util.Scanner;
import java.util.ArrayList;

public class UI {
    private ArrayList<Task> tasks;
    private Storage storage;

    public UI() {
        storage = new Storage();
        tasks = new ArrayList<>(storage.loadTasks());
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }
    public void greet() {
        printLine();
        System.out.println(" Yo! I'm DNar");
        System.out.println(" Can you?");
        printLine();
    }
    public void exit() {
        printLine();
        System.out.println(" Bye. Don't come back!");
        printLine();
    }
    public void runbot() {
        greet();
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            command = scanner.nextLine();
            try {
                if (command.equals("bye")) {
                    exit();
                    break;
                } else if (command.equals("list")) {
                    listTasks();
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]);
                    markTask(index);
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]);
                    unmarkTask(index);
                } else if (command.startsWith("todo")) {
                    String description = command.substring(4);
                    if (description.isEmpty()) {
                        throw new DNarException("NOOO!!! The description of a todo cannot be empty.");
                    }
                    addTask(new ToDo(description));
                } else if (command.startsWith("deadline")) {
                    String[] parts = command.substring(8).split(" /by ");
                    if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                        throw new DNarException("NOOO!!! Both description and deadline time cannot be empty.");
                    }
                    addTask(new Deadline(parts[0], parts[1]));
                } else if (command.startsWith("event")) {
                    String[] parts = command.substring(5).split(" /");
                    if (parts.length < 3 || parts[0].isEmpty() || parts[1].substring(4).isEmpty() || parts[2].substring(3).isEmpty()) {
                        throw new DNarException("NOOO!!! All fields for the event must be filled in.");
                    }
                    addTask(new Event(parts[0], parts[1].substring(4), parts[2].substring(3)));
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]);
                    deleteTask(index);
                } else {
                    throw new DNarException("HUHH!!! WDYM :-(");
                }
            } catch (DNarException e) {
                printLine();
                System.out.println(" " + e.getMessage());
                printLine();
            }
        }
        scanner.close();
    }
    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
        printLine();
        System.out.println(" Got ya.");
        System.out.println(" More??? Added: " + task);
        System.out.println(tasks.size() + " tasks in the list. Do you have more?");
        printLine();
    }
    public void listTasks() {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println(" Your task list is empty!");
            return;
        }
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }
    public void deleteTask(int index) {
        try {
            if (index < 1 || index > tasks.size()) {
                throw new DNarException("This does not exist!! Try 1 to number of tasks instead:D");
            }
            Task removedTask = tasks.remove(index - 1);
            storage.saveTasks(tasks);
            printLine();
            System.out.println(" Shhh.. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(tasks.size() + " tasks in the list. Delete more?");
            printLine();
        } catch (DNarException e) {
            printLine();
            System.out.println(" " + e.getMessage());
            printLine();
        }
    }

    public void markTask(int index) {
        try {
            if (index < 1 || index > tasks.size()) {
                throw new DNarException("This does not exist!! Try 1 to number of tasks instead:D");
            }
            tasks.get(index - 1).markDone();
            storage.saveTasks(tasks);
            printLine();
            System.out.println(" That's crazyy!! Marking this task as done:");
            System.out.println("   " + tasks.get(index - 1));
            printLine();
        } catch (DNarException e) {
            printLine();
            System.out.println(" " + e.getMessage());
            printLine();
        }
    }

    public void unmarkTask(int index) {
        try {
            if (index < 1 || index > tasks.size()) {
                throw new DNarException("This does not exist!! Try 1 to number of tasks instead:D");
            }
            tasks.get(index - 1).markNotDone();
            storage.saveTasks(tasks);
            printLine();
            System.out.println(" What have you done!! This task is undone:");
            System.out.println("   " + tasks.get(index - 1));
            printLine();
        } catch (DNarException e) {
            printLine();
            System.out.println(" " + e.getMessage());
            printLine();
        }
    }
}
