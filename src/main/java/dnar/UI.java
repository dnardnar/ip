package dnar;

import java.util.Scanner;
import java.util.List;

/**
 * Handles interactions with the user, including displaying messages
 * and reading user input.
 */
public class UI {
    private final Scanner scanner;

    /**
     * Constructs a new UI instance with a scanner for user input.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line for formatting output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the welcome message.
     */
    public void greet() {
        showLine();
        System.out.println(" Yo! I'm DNar");
        System.out.println(" Can you?");
        showLine();
    }

    /**
     * Displays the exit message.
     */
    public void exit() {
        showLine();
        System.out.println(" Bye. Don't come back!");
        showLine();
    }

    /**
     * Reads and returns the next user input command.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The TaskList containing the user's tasks.
     */
    public void listTasks(TaskList taskList) {
        showLine();
        if (taskList.size() == 0) {
            System.out.println(" Your task list is empty!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.getTask(i));
            }
        }
        showLine();
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after adding.
     */
    public void showAddedTask(Task task, int size) {
        showLine();
        System.out.println(" Got ya.");
        System.out.println(" More??? Added: " + task);
        System.out.println(size + " tasks in the list. Do you have more?");
        showLine();
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks after deletion.
     */
    public void showDeletedTask(Task task, int size) {
        showLine();
        System.out.println(" Shhh.. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(size + " tasks in the list. Delete more?");
        showLine();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkDone(Task task) {
        showLine();
        System.out.println(" That's crazyy!! Marking this task as done:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays a message confirming that a task has been unmarked as done.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkDone(Task task) {
        showLine();
        System.out.println(" What have you done!! This task is undone:");
        System.out.println("   " + task);
        showLine();
    }

    public void showMatchingTasks(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println(" " + message);
        showLine();
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        showLine();
        System.out.println(" Error loading tasks. Starting with an empty list.");
        showLine();
    }
}
