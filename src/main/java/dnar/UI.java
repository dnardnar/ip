package dnar;

import java.util.Scanner;

public class UI {
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        showLine();
        System.out.println(" Yo! I'm DNar");
        System.out.println(" Can you?");
        showLine();
    }

    public void exit() {
        showLine();
        System.out.println(" Bye. Don't come back!");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

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

    public void showAddedTask(Task task, int size) {
        showLine();
        System.out.println(" Got ya.");
        System.out.println(" More??? Added: " + task);
        System.out.println(size + " tasks in the list. Do you have more?");
        showLine();
    }

    public void showDeletedTask(Task task, int size) {
        showLine();
        System.out.println(" Shhh.. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(size + " tasks in the list. Delete more?");
        showLine();
    }

    public void showMarkDone(Task task) {
        showLine();
        System.out.println(" That's crazyy!! Marking this task as done:");
        System.out.println("   " + task);
        showLine();
    }

    public void showUnmarkDone(Task task) {
        showLine();
        System.out.println(" What have you done!! This task is undone:");
        System.out.println("   " + task);
        showLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println(" " + message);
        showLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println(" Error loading tasks. Starting with an empty list.");
        showLine();
    }
}
