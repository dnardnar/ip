package dnar;

import java.util.List;
import java.util.Scanner;

/**
 * Handles interactions with the user, including displaying messages and reading user input.
 */
public class UI {

    private static final String LINE = "____________________________________________________________";
    private static final String WELCOME_MESSAGE_1 = " Yo! I'm DNar";
    private static final String WELCOME_MESSAGE_2 = " Can you?";
    private static final String EXIT_MESSAGE = " Bye. Don't come back!";
    private static final String EMPTY_TASK_LIST_MESSAGE = " Your task list is empty!";
    private static final String TASK_LIST_HEADER = " Here are the tasks in your list:";
    private static final String ADDED_TASK_MESSAGE_1 = " Got ya.";
    private static final String ADDED_TASK_MESSAGE_2 = " More??? Added: ";
    private static final String ADDED_TASK_MESSAGE_3 = " tasks in the list. Do you have more?";
    private static final String DELETED_TASK_MESSAGE_1 = " Shhh.. I've removed this task:";
    private static final String DELETED_TASK_MESSAGE_2 = " tasks in the list. Delete more?";
    private static final String MARK_DONE_MESSAGE_1 = " That's crazyy!! Marking this task as done:";
    private static final String UNMARK_DONE_MESSAGE_1 = " What have you done!! This task is undone:";
    private static final String LOADING_ERROR_MESSAGE = " Error loading tasks. Starting with an empty list.";
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
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message.
     */
    public void greet() {
        showLine();
        System.out.println(WELCOME_MESSAGE_1);
        System.out.println(WELCOME_MESSAGE_2);
        showLine();
    }

    /**
     * Displays the exit message.
     */
    public void exit() {
        showLine();
        System.out.println(EXIT_MESSAGE);
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
            System.out.println(EMPTY_TASK_LIST_MESSAGE);
        } else {
            System.out.println(TASK_LIST_HEADER);
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
        System.out.println(ADDED_TASK_MESSAGE_1);
        System.out.println(ADDED_TASK_MESSAGE_2 + task);
        System.out.println(size + ADDED_TASK_MESSAGE_3);
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
        System.out.println(DELETED_TASK_MESSAGE_1);
        System.out.println("   " + task);
        System.out.println(size + DELETED_TASK_MESSAGE_2);
        showLine();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkDone(Task task) {
        showLine();
        System.out.println(MARK_DONE_MESSAGE_1);
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
        System.out.println(UNMARK_DONE_MESSAGE_1);
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays the matching tasks from the search keyword
     *
     * @param tasks the tasks that has the keyword
     */
    public void showMatchingTasks(List<Task> tasks) {
        showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
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
        System.out.println(LOADING_ERROR_MESSAGE);
        showLine();
    }

    public void showEditSuccess(Task task) {
        showLine();
        System.out.println("Successfully updated the task:");
        System.out.println("   " + task);
        showLine();
    }

}
