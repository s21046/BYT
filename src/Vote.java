public class Vote {
    private static int uniqueId = 0;
    private int id;
    private String explanation;
    private Assignee assignee;
    private int assigneeId;
    private int taskId;

    public Vote(String explanation, Assignee person, int taskId) {
        this.id = uniqueId++;
        this.explanation = explanation;
        this.assignee = person;
        this.taskId = taskId;
    }

    public void setAssignee(Assignee assignee){
        this.assignee = assignee;
    }

    public Assignee getAssignee(){
        return assignee;
    }

    public void setExplanation(String explanation){
        this.explanation = explanation;
    }

    public String getExplanation(){
        return explanation;
    }

    public static int getUniqueId() {
        return uniqueId;
    }

    public static void setUniqueId(int uniqueId) {
        Vote.uniqueId = uniqueId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
