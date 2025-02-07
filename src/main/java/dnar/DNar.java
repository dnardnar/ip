package dnar;

public class DNar {
    private final Storage storage;
    private TaskList taskList;
    private final UI ui;

    public DNar(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            String command = ui.readCommand();
            if (command.equals("bye")) {
                isRunning = false;
                ui.exit();
            } else {
                Parser.parse(command, taskList, ui, storage);
            }
        }
    }

    public static void main(String[] args) {
        new DNar("data/DNar.txt").run();
    }
    public String getResponse(String input) {
        return "DNar heard: " + input;
    }
}
