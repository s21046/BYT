public class Review {
    private static int uniqueId = 0;
    private int id;
    private String description;
    private int assigneeId;
    private int taskId;
    private boolean approved;

    public Review(boolean approved, String description, int assigneeId, int taskId) {
        this.id = uniqueId++;
        this.approved = approved;
        this.description = description;
        this.assigneeId = assigneeId;
        this.taskId = taskId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public static int getUniqueId() {
        return uniqueId;
    }

    public int getId() {
        return id;
    }

    public boolean isApproved() {
        return approved;
    }

    public String getDescription() {
        return description;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public int getTaskId() {
        return taskId;
    }

    public static void setUniqueId(int uniqueId) {
        Review.uniqueId = uniqueId;
    }
}
