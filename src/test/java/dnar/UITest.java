package dnar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UITest {

    private UI ui;
    private TaskList taskList;
    private static final String TEST_FILE_PATH = "data/test_DNar.txt";
    private Storage storage = new Storage(TEST_FILE_PATH);
    @BeforeEach
    public void setUp() {
        ui = new UI();
        taskList = new TaskList(storage);
    }

    @Test
    public void testListTasksWithNoTasks() {
        taskList = new TaskList(storage);
        ui.listTasks(taskList);  // Should print "Your task list is empty!"
    }

    @Test
    public void testListTasksWithTasks() {
        Task task = new ToDo("Test ToDo Task");
        taskList.addTask(task);
        ui.listTasks(taskList);  // Should list the task
    }
}
