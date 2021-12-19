import ApplicationExceptions.StringTooShortException;

public class Vote {
    private static int uniqueId = 0;
    private int id;
    private String explanation;
    private int votedForId;
    private int voterId;
    private Task task;

    public Vote(String explanation, int votedForId, int voterId, Task task) {
        this.id = uniqueId++;
        this.explanation = explanation;
        this.votedForId = votedForId;
        this.voterId = voterId;
        this.task = task;
    }

    public void setExplanation(String explanation) throws StringTooShortException {
        if(explanation == null) throw new IllegalArgumentException("Argument cannot be null");
        if(explanation.length()<7) throw new StringTooShortException();
        this.explanation = explanation;
    }

    public String getExplanation(){
        return explanation;
    }

    public static int getUniqueId() {
        return uniqueId;
    }

    public static void setUniqueId(int uniqueId) {
        if(uniqueId < 0) throw new IllegalArgumentException("Argument cannot be a negative integer.");
        Vote.uniqueId = uniqueId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id < 0) throw new IllegalArgumentException("Argument cannot be a negative integer.");
        if(task.getVotes_list().stream().anyMatch(v -> v.getId() == id))
            throw new IllegalArgumentException("Vote with this id already exists.");
        this.id = id;
    }

    public int getVotedForId() {
        return votedForId;
    }

    public void setVotedForId(int votedForId) {
        if(task.getTeamAssigned().getAssignees().stream().noneMatch(a -> a.getId() == votedForId))
            throw new IllegalArgumentException("No such Assignee with such ID found in the Task's Team.");
        this.votedForId = votedForId;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        if(task.getTeamAssigned().getAssignees().stream().noneMatch(a -> a.getId() == voterId))
            throw new IllegalArgumentException("No such Assignee with such ID found in the Task's Team.");
        this.voterId = voterId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        if(task == null) throw new IllegalArgumentException("Argument cannot be null.");
        this.task = task;
    }
}
