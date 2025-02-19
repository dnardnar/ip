package dnar;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.util.List;

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

    private VBox dialogContainer;
    private Image userImage;
    private Image dnarImage;

    /**
     * Constructs a new UI instance with a scanner for user input.
     */
    public UI() {
        // Default constructor
    }

    public UI(VBox dialogContainer, Image userImage, Image dnarImage) {
        this.dialogContainer = dialogContainer;
        this.userImage = userImage;
        this.dnarImage = dnarImage;
    }

    /**
     * Prints a horizontal line for formatting output.
     */
    public void showLine() {
        addResponse(LINE);
    }

    /**
     * Displays the welcome message.
     */
    public void greet() {
        showLine();
        addResponse(WELCOME_MESSAGE_1);
        addResponse(WELCOME_MESSAGE_2);
    }

    /**
     * Displays the exit message.
     */
    public void exit() {
        showLine();
        addResponse(EXIT_MESSAGE);
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The TaskList containing the user's tasks.
     */
    public void listTasks(TaskList taskList) {
        showLine();
        if (taskList.size() == 0) {
            addResponse(EMPTY_TASK_LIST_MESSAGE);
        } else {
            addResponse(TASK_LIST_HEADER);
            for (int i = 0; i < taskList.size(); i++) {
                addResponse((i + 1) + ". " + taskList.getTask(i));
            }
        }
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after adding.
     */
    public void showAddedTask(Task task, int size) {
        showLine();
        addResponse(ADDED_TASK_MESSAGE_1);
        addResponse(ADDED_TASK_MESSAGE_2 + task);
        addResponse(size + ADDED_TASK_MESSAGE_3);
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks after deletion.
     */
    public void showDeletedTask(Task task, int size) {
        showLine();
        addResponse(DELETED_TASK_MESSAGE_1);
        addResponse("   " + task);
        addResponse(size + DELETED_TASK_MESSAGE_2);
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkDone(Task task) {
        showLine();
        addResponse(MARK_DONE_MESSAGE_1);
        addResponse("   " + task);
    }

    /**
     * Displays a message confirming that a task has been unmarked as done.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkDone(Task task) {
        showLine();
        addResponse(UNMARK_DONE_MESSAGE_1);
        addResponse("   " + task);
    }

    /**
     * Displays the matching tasks from the search keyword
     *
     * @param tasks the tasks that has the keyword
     */
    public void showMatchingTasks(List<Task> tasks) {
        showLine();
        addResponse("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            addResponse((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        addResponse(" " + message);
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        showLine();
        addResponse(LOADING_ERROR_MESSAGE);
    }

    public void showEditSuccess(Task task) {
        showLine();
        addResponse("Successfully updated the task:");
        addResponse("   " + task);
    }

    private void addResponse(String text) {
        Platform.runLater(() -> {
            dialogContainer.getChildren().add(DialogBox.getDnarDialog(text, dnarImage));
        });
    }
}
