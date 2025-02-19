package dnar;

public class DNar {
    private static final String DEFAULT_FILE_PATH = "data/DNar.txt";
    private final Storage storage;
    private TaskList taskList;
    private UI ui;

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
     * Starts the DNar application.
     * Initializes the user interface, storage, and application logic.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        UI ui = new UI();
        Storage storage = new Storage(DEFAULT_FILE_PATH);
        new DNar(DEFAULT_FILE_PATH, ui, storage);
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }
}
