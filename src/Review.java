public class Review {
    //TODO implement id uniqueness
    private int id;
    private boolean approved;
    private String description;

    private int assigneeId, taskId;

    public Review(int id, boolean approved, String description,
                  int assigneeId, int taskId) {
        this.id = id;
        this.approved = approved;
        this.description = description;
        this.assigneeId = assigneeId;
        this.taskId = taskId;
    }
}
