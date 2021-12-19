public class Vote {
    private static int uniqueId = 0;
    private int id;
    private String explanation;
    private int votedForId;
    private int voterId;
    private int taskId;

    public Vote(String explanation, int votedForId, int voterId, int taskId) {
        this.id = uniqueId++;
        this.explanation = explanation;
        this.votedForId = votedForId;
        this.voterId = voterId;
        this.taskId = taskId;
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
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getVotedForId() {
        return votedForId;
    }

    public void setVotedForId(int votedForId) {
        this.votedForId = votedForId;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }
}
