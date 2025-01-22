import java.util.Scanner;
public class UI {
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
            }
            printLine();
            System.out.println(" Echo---ing: " + command);
            printLine();
        }
        scanner.close();
    }
}