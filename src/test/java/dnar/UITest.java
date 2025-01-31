package dnar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UITest {

    private UI ui;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        ui = new UI();
        taskList = new TaskList();
    }

    @Test
    public void testListTasksWithNoTasks() {
        taskList = new TaskList();
        ui.listTasks(taskList);  // Should print "Your task list is empty!"
    }

    @Test
    public void testListTasksWithTasks() {
        Task task = new ToDo("Test ToDo Task");;
        taskList.addTask(task, new Storage("testData.txt"));
        ui.listTasks(taskList);  // Should list the task
    }
}
