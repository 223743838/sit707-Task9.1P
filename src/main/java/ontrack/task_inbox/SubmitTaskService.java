package ontrack.task_inbox;

import java.util.*;

public class SubmitTaskService {
    private final Map<String, List<String>> taskDatabase = new HashMap<>();

    public boolean submitTask(String studentId, String taskTitle) {
        if (studentId == null || studentId.isEmpty() || taskTitle == null || taskTitle.isEmpty()) {
            return false;
        }
        taskDatabase.computeIfAbsent(studentId, k -> new ArrayList<>()).add(taskTitle);
        return true;
    }

    public List<String> getTasksByStudent(String studentId) {
        return taskDatabase.getOrDefault(studentId, new ArrayList<>());
    }
}