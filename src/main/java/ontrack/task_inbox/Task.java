package ontrack.task_inbox;

public class Task {
    private String title;
    private String status;

    public Task(String title, String status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
}