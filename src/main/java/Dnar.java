import java.util.Scanner;
public class Dnar {
    public static void main(String[] args) {
        printLine();
        System.out.println(" Yo! I'm DNar");
        System.out.println(" Can you?");
        printLine();
        System.out.println(" Bye. Don't come back!");
        printLine();
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }
            printLine();
            System.out.println(" Thank you for your input, echo----ing: " + command);
            printLine();
        }
        scanner.close();
    }
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}