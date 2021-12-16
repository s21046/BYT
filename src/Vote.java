public class Vote {
    private int id;
    private String explanation;
    private Assignee person;

    private int assigneeId, taskId;

    public Vote(int id, String explanation, Assignee person,
                int assigneeId, int taskId) {
        this.id = id;
        this.explanation = explanation;
        this.person = person;
        this.assigneeId = assigneeId;
        this.taskId = taskId;
    }
}
