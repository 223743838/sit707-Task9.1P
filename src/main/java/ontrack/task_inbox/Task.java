package ontrack.task_inbox;

public class Task {
    private String taskId;
    private String studentId;
    private String title;
    private String status;
    private String feedback;
    
    
    
	public Task(String taskId, String studentId, String title, String status, String feedback) {
		super();
		this.taskId = taskId;
		this.studentId = studentId;
		this.title = title;
		this.status = status;
		this.feedback = feedback;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}
