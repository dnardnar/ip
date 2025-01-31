package dnar;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.List;

public class StorageTest {

    private static final String TEST_FILE_PATH = "data/test_DNar.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveAndLoadTasks() {
        Task task1 = new ToDo("Test ToDo");
        Task task2 = new Deadline("Test Deadline", "2025-12-31");
        Task task3 = new Event("Test Event", "from Mon 2pm", "4pm");

        List<Task> tasksToSave = List.of(task1, task2, task3);
        System.out.println(task1.toDataString());
        System.out.println(task2.toDataString());
        System.out.println(task3.toDataString());
        storage.saveTasks(tasksToSave);

        List<Task> loadedTasks = storage.loadTasks();
        assertEquals(3, loadedTasks.size());
    }

    @Test
    public void testLoadEmptyFile() {
        storage.saveTasks(List.of()); // Save an empty list

        List<Task> loadedTasks = storage.loadTasks();

        assertTrue(loadedTasks.isEmpty());
    }

    @Test
    public void testLoadCorruptedFile() throws IOException {
        // Write a corrupted entry to the test file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.write("INVALID DATA");
        }

        List<Task> loadedTasks = storage.loadTasks();

        assertTrue(loadedTasks.isEmpty()); // Should skip corrupted data and return an empty list
    }
}
