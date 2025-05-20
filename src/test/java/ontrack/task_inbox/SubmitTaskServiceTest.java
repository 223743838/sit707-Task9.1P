package ontrack.task_inbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class SubmitTaskServiceTest {
    SubmitTaskService service = new SubmitTaskService();

    @Test
    public void testSubmitValidTask() {
        boolean result = service.submitTask("S101", "Assignment 1");
        assertTrue(result);
        List<String> tasks = service.getTasksByStudent("S101");
        assertEquals(1, tasks.size());
        assertEquals("Assignment 1", tasks.get(0));
    }

    @Test
    public void testSubmitTaskWithEmptyStudentId() {
        boolean result = service.submitTask("", "Assignment 2");
        assertFalse(result);
    }

    @Test
    public void testSubmitTaskWithNullTitle() {
        boolean result = service.submitTask("S101", null);
        assertFalse(result);
    }

    @Test
    public void testMultipleSubmissions() {
        service.submitTask("S102", "Task A");
        service.submitTask("S102", "Task B");
        List<String> tasks = service.getTasksByStudent("S102");
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains("Task A"));
        assertTrue(tasks.contains("Task B"));
    }

    @Test
    public void testSubmitTaskWithNullValues() {
        boolean result = service.submitTask(null, null);
        assertFalse(result);
    }
}