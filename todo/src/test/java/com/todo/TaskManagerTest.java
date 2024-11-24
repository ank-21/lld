package test.java.com.todo;

import main.java.com.todo.Task;
import main.java.com.todo.TaskManager;
import main.java.com.todo.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = TaskManager.getInstance();
    }

    @Test
    public void testAddTask() {
        Task task = taskManager.addTask("Solve LLD", "Solve some LLD problems", Arrays.asList("LLD", "Machine Coding", "Low Level Design", "Medium", "Interview"), LocalDateTime.of(2024, 11, 26, 23, 59), 3);
        assertNotNull(task, "Task should not be null after being added.");
        assertEquals("Solve LLD", task.getName());
    }

    @Test
    public void testUpdateTask() {
        Task task = taskManager.addTask("Solve LLD", "Solve some LLD problems", Arrays.asList("LLD", "Machine Coding", "Low Level Design", "Medium", "Interview"), LocalDateTime.of(2024, 11, 26, 23, 59), 3);
        task.setStatus(Status.INPROGRESS);
        taskManager.updateTask(task);
        assertEquals(Status.INPROGRESS, task.getStatus(), "Task status should be updated to IN_PROGRESS.");
    }

    @Test
    public void testRemoveTask() {
        Task task = taskManager.addTask("Test check", "Testing on code", Arrays.asList("Test", "Junit", "Interview"), LocalDateTime.of(2024, 11, 26, 23, 59), 3);
        taskManager.removeTask(task);
        assertFalse(taskManager.getTasks().containsKey(task), "Task should be removed from the task list.");
    }
}
