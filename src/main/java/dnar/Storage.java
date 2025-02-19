package dnar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the saving and loading of tasks from a file.
 */
public class Storage {
    private static final String DELIMITER = " \\| ";
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     * Ensures the file and its parent directory exist.
     *
     * @param filePath The relative or absolute path to the storage file.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        this.filePath = filePath;
        ensureFileExists();
    }

    /**
     * Ensures that the storage file and its parent directory exist. Creates them if they do not exist.
     */
    private void ensureFileExists() {
        File file = new File(filePath);
        createDirectories(file.getParentFile());
        createFile(file);
    }

    private void createDirectories(File directory) {
        if (directory != null && !directory.exists()) {
            if (!directory.mkdirs()) {
                System.err.println("Failed to create directories.");
            }
        }
    }

    private void createFile(File file) {
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.err.println("Failed to create new file.");
                }
                // Assertion to ensure the file exist after creation
                assert file.exists() : "File should exist after creation";
            } catch (IOException e) {
                System.err.println("Error creating storage file: " + e.getMessage());
            }
        }
    }


    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                assert task != null : "Each task should not be null";
                writeTaskToFile(writer, task); // Use the helper method to write each task
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }


    private void writeTaskToFile(BufferedWriter writer, Task task) throws IOException {
        writer.write(task.toDataString());
        writer.newLine();
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks retrieved from the file.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a line from the storage file and converts it into a Task object.
     * Handles different task types such as ToDo, Deadline, and Event.
     *
     * @param line The line from the file to parse.
     * @return A Task object, or null if the line is corrupted or invalid.
     */
    private Task parseTask(String line) {
        // Ensure the line is not null or empty
        assert line != null : "Line should not be null";
        if (line.isEmpty()) {
            return null;
        }

        try {
            // Split the line into parts using the defined delimiter
            String[] parts = line.split(DELIMITER);
            assert parts.length >= 3 : "Line should have at least three parts";

            // Extract task details
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            // Handle different task types
            switch (type) {
                case TASK_TYPE_TODO:
                    return new ToDo(description, isDone);

                case TASK_TYPE_DEADLINE:
                    if (parts.length < 4) {
                        System.err.println("Skipping corrupted entry: " + line);
                        return null;
                    }
                    String deadlineDate = DateTimeParser.reformatDateForStorage(parts[3]);
                    return new Deadline(description, deadlineDate, isDone);

                case TASK_TYPE_EVENT:
                    if (parts.length < 5) {
                        System.err.println("Skipping corrupted entry: " + line);
                        return null;
                    }
                    return new Event(description, parts[3], parts[4], isDone);

                default:
                    System.err.println("Skipping corrupted entry: " + line);
                    return null;
            }
        } catch (Exception e) {
            System.err.println("Skipping corrupted entry: " + line);
            return null;
        }
    }
}
