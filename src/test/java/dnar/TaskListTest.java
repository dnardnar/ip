package dnar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;
    private Storage storage;
    private static final String TEST_FILE_PATH = "data/test_DNar.txt";
    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
        taskList = new TaskList(storage);
    }

    @Test
    public void testAddTask() {
        Task task = new ToDo("Test ToDo Task"); // Use a concrete subclass
        taskList.addTask(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testDeleteTask() {
        Task task = new ToDo("Test ToDo Task");
        taskList.addTask(task);
        try {
            Task deletedTask = taskList.deleteTask(1); // Attempt to delete the task
            assertEquals(0, taskList.size()); // Verify that the list is now empty
            assertEquals(task, deletedTask); // Verify that the correct task was deleted
        } catch (DNarException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }

    @Test
    public void testValidateIndex() {
        Task task = new ToDo("Test ToDo Task");
        taskList.addTask(task);

        assertDoesNotThrow(() -> taskList.validateIndex(0));
        assertThrows(DNarException.class, () -> taskList.validateIndex(1));
    }
}
