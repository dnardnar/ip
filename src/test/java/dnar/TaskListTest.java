package dnar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        storage = new Storage("testData.txt");
    }

    @Test
    public void testAddTask() {
        Task task = new ToDo("Test ToDo Task"); // Use a concrete subclass
        taskList.addTask(task, storage);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testDeleteTask() {
        Task task = new ToDo("Test ToDo Task");
        taskList.addTask(task, storage);
        Task deletedTask = taskList.deleteTask(0, storage);

        assertEquals(0, taskList.size());
        assertEquals(task, deletedTask);
    }

    @Test
    public void testValidateIndex() {
        Task task = new ToDo("Test ToDo Task");
        taskList.addTask(task, storage);

        assertDoesNotThrow(() -> taskList.validateIndex(0));
        assertThrows(DNarException.class, () -> taskList.validateIndex(1));
    }
}
