package ontrack.task_inbox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTaskService {
    TaskService service;

    @BeforeEach
    void init() {
        service = new TaskService();
    }

    @Test
    void testSubmitValidTask() {
        assertTrue(service.submitTask("S101", "Assignment 1"));
    }

    @Test
    void testSubmitTaskWithNullTitle() {
        assertFalse(service.submitTask("S102", null));
    }

    @Test
    void testFetchTasksByStudent() {
        service.submitTask("S103", "Task 1");
        assertEquals(1, service.getTasksByStudent("S103").size());
    }

    @Test
    void testUpdateTaskStatus() {
        service.submitTask("S104", "Task 2");
        Task task = service.getTasksByStudent("S104").get(0);
        assertTrue(service.updateTaskStatus(task.getTaskId(), "Reviewed", "Good work"));
    }

    @Test
    void testChatMessageFlow() {
        service.submitTask("S105", "Task 3");
        Task task = service.getTasksByStudent("S105").get(0);
        assertTrue(service.postChatMessage(task.getTaskId(), "Please explain", "Student"));
        assertFalse(service.postChatMessage(null, "Test", "Student"));
    }
    
    @Test
    void testGetChatHistoryReturnsEmptyForNewTask() {
        service.submitTask("S200", "Sample Task");
        Task task = service.getTasksByStudent("S200").get(0);
        List<String> chat = service.getChatHistory(task.getTaskId());

        assertNotNull(chat);
        assertEquals(0, chat.size()); 
    }
    @Test
    void testGetChatHistoryAfterAddingMessages() {
        service.submitTask("S201", "Chat Task");
        Task task = service.getTasksByStudent("S201").get(0);

        service.postChatMessage(task.getTaskId(), "Hello Tutor", "Student");
        service.postChatMessage(task.getTaskId(), "Hi Sushma", "Tutor");

        List<String> chat = service.getChatHistory(task.getTaskId());

        assertEquals(2, chat.size());
        assertTrue(chat.get(0).contains("Hello Tutor"));
        assertTrue(chat.get(1).contains("Hi Sushma"));
    }

    @Test
    void testBulkTaskSubmissionPerformance() {
        for (int i = 0; i < 100; i++) {
            assertTrue(service.submitTask("S" + i, "Task " + i));
        }

        // Count all tasks submitted
        int totalTasks = 0;
        for (int i = 0; i < 100; i++) {
            totalTasks += service.getTasksByStudent("S" + i).size();
        }

        assertEquals(100, totalTasks);
    }

    @Test
    void testGetTaskDetailsWithInvalidId() {
        Task result = service.getTaskDetails("non-existent-id");
        assertNull(result);
    }
    
    @Test
    void testUpdateStatusInvalidId() {
        boolean updated = service.updateTaskStatus("invalid-id", "Reviewed", "Good job");
        assertFalse(updated);
    }


}
