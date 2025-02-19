package dnar;

public class DNar {
    private static final String DEFAULT_FILE_PATH = "data/DNar.txt";
    private final Storage storage;
    private TaskList taskList;
    private final UI ui;

    /**
     * Constructs a DNar object with the given file path. Initializes the UI, Storage, and TaskList.
     * If loading tasks from storage fails, initializes an empty TaskList.
     *
     * @param filePath The path to the storage file.
     * @param ui       The user interface object.
     * @param storage  The storage object.
     */
    public DNar(String filePath, UI ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        try {
            taskList = new TaskList(storage.loadTasks(), storage);
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList(storage);
        }
    }

    /**
     * Runs the main loop of the DNar application. Reads commands from the UI, parses them, and executes them
     * until the "bye" command is entered.
     */
    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            String command = ui.readCommand();
            if ("bye".equals(command)) {
                isRunning = false;
                ui.exit();
            } else {
                try {
                    Parser.parse(command, taskList, ui, storage);
                } catch (Exception e) {
                    ui.showError("An unexpected error occurred while parsing the command.");
                }
            }
        }
    }

    /**
     * Starts the DNar application.
     * Initializes the user interface, storage, and application logic.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        UI ui = new UI();
        Storage storage = new Storage(DEFAULT_FILE_PATH);
        new DNar(DEFAULT_FILE_PATH, ui, storage).run();
    }

    /**
     * Returns a response string based on the input.
     *
     * @param input The input string.
     * @return A string indicating that DNar heard the input.
     */
    public String getResponse(String input) {
        return "DNar heard: " + input;
    }
}
