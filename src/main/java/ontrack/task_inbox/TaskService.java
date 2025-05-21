package ontrack.task_inbox;

import java.util.*;

public class TaskService {
    private Map<String, List<Task>> taskMap = new HashMap<>();
    private Map<String, List<String>> chatMap = new HashMap<>();

    public boolean submitTask(String studentId, String title) {
        if (studentId == null || title == null || title.trim().isEmpty()) return false;
        Task task = new Task(UUID.randomUUID().toString(), studentId, title, "Submitted", "");
        taskMap.computeIfAbsent(studentId, k -> new ArrayList<>()).add(task);
        return true;
    }

    public List<Task> getTasksByStudent(String studentId) {
        return taskMap.getOrDefault(studentId, new ArrayList<>());
    }

    public Task getTaskDetails(String taskId) {
        return taskMap.values().stream()
                .flatMap(List::stream)
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst().orElse(null);
    }

    public boolean postChatMessage(String taskId, String message, String sender) {
        if (taskId == null || message == null || sender == null) return false;
        String msg = sender + ": " + message;
        chatMap.computeIfAbsent(taskId, k -> new ArrayList<>()).add(msg);
        return true;
    }

    public List<String> getChatHistory(String taskId) {
        return chatMap.getOrDefault(taskId, new ArrayList<>());
    }

    public boolean updateTaskStatus(String taskId, String status, String feedback) {
        Task task = getTaskDetails(taskId);
        if (task == null || status == null) return false;
        task.setStatus(status);
        task.setFeedback(feedback);
        return true;
    }
}
