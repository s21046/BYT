public class Review {
    private static int uniqueId = 0;
    private int id;
    private boolean approved;
    private String description;

    private int assigneeId, taskId;

    public Review(boolean approved, String description,
                  int assigneeId, int taskId) {
        this.id = uniqueId++;
        this.approved = approved;
        this.description = description;
        this.assigneeId = assigneeId;
        this.taskId = taskId;
    }
}
