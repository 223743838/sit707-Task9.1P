package ontrack.task_inbox;

import java.util.ArrayList;
import java.util.List;



public class TaskInbox {
    public static List<Task> getTasksForStudent(String studentId) {
        List<Task> taskList = new ArrayList<>();
        if (studentId.equals("S123")) {
            taskList.add(new Task("Assignment 1", "Submitted"));
            taskList.add(new Task("Assignment 2", "Graded"));
        }
        return taskList;
    }
}