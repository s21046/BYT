public class Vote {
    private static int uniqueId = 0;
    private int id;
    private String explanation;
    private Assignee person;

    private int assigneeId, taskId;

    public Vote(String explanation, Assignee person,
                int taskId) {
        this.id = uniqueId++;
        this.explanation = explanation;
        this.person = person;
        this.taskId = taskId;
    }
}
