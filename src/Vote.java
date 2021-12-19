public class Vote {
    private static int uniqueId = 0;
    private int id;
    private String explanation;
    private Assignee assignee;

    private int assigneeId, taskId;

    public Vote(String explanation, Assignee person,
                int taskId) {
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
}
