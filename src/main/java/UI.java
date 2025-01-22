import java.util.Scanner;
import java.util.ArrayList;
public class UI {
    private ArrayList<String> tasks = new ArrayList<>();
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
            } else {
                addTask(command);
            }
        }
        scanner.close();
    }
    public void addTask(String task) {
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
}
