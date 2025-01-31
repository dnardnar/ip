package dnar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the saving and loading of tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     * Ensures the file and its parent directory exist.
     *
     * @param filePath The relative or absolute path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureFileExists();
    }

    /**
     * Ensures that the storage file and its parent directory exist.
     * Creates them if they do not exist.
     */
    private void ensureFileExists() {
        File file = new File(filePath);
        File directory = file.getParentFile();

        try {
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
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
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
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
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    return new ToDo(description, isDone);
                case "D":
                    String deadlineDate = DateTimeParser.unparseDate(parts[3]);
                    return new Deadline(description, deadlineDate, isDone);
                case "E":
                    return new Event(description, parts[3], parts[4], isDone);
                default:
                    System.out.println("Skipping corrupted entry: " + line);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Skipping corrupted entry: " + line);
            return null;
        }
    }
}
