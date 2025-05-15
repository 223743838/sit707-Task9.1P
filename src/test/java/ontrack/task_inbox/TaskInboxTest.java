package ontrack.task_inbox;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class TaskInboxTest {

    @Test
    public void testGetTasksForValidStudent() {
        List<Task> tasks = TaskInbox.getTasksForStudent("S123");
        assertEquals(2, tasks.size());
        assertEquals("Assignment 1", tasks.get(0).getTitle());
        assertEquals("Graded", tasks.get(1).getStatus());
    }

    @Test
    public void testGetTasksForUnknownStudent() {
        List<Task> tasks = TaskInbox.getTasksForStudent("S707");
        assertEquals(0, tasks.size());
    }
}