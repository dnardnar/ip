import java.util.Scanner;
import java.util.ArrayList;
public class UI {
    private ArrayList<Task> tasks = new ArrayList<>();
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
            if (command.equals("bye")) {
                exit();
                break;
            } else if(command.equals("list")) {
                listTasks();
            } else if(command.startsWith("mark ")){
                int index = Integer.parseInt(command.split(" ")[1]);
                markTask(index);
            } else if(command.startsWith("unmark ")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                unmarkTask(index);
            } else {
                addTask(command);
            }
        }
        scanner.close();
    }
    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        printLine();
        System.out.println(" More??? Added: " + task);
        printLine();
    }
    public void listTasks() {
        printLine();
        System.out.println(" Do this task nowww!!:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }
    public void markTask(int index) {
        if (index < 1 || index > tasks.size()) {
            printLine();
            System.out.println(" XXX try again!!");
            printLine();
            return;
        }
        tasks.get(index - 1).markDone();
        printLine();
        System.out.println(" That's crazyy!! Marking this task as done:");
        System.out.println("   " + tasks.get(index - 1));
        printLine();
    }
    public void unmarkTask(int index) {
        if (index < 1 || index > tasks.size()) {
            printLine();
            System.out.println(" XXX try again!!");
            printLine();
            return;
        }
        tasks.get(index - 1).markNotDone();
        printLine();
        System.out.println(" What have you done!! This task is undone:");
        System.out.println("   " + tasks.get(index - 1));
        printLine();
    }
}
