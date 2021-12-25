import ApplicationExceptions.StringTooShortException;

public class Vote {
    private int id;
    private Task task;
    private String explanation;

    //TODO set up checks for these FKs in the {set} methods -> call proper exceptions -> add to tests
    private int votedForId;
    private int voterId;

    public Vote(Task task, String explanation, int votedForId, int voterId) throws StringTooShortException {
        if (votedForId < 0 || voterId < 0) { throw new IllegalArgumentException("id cannot be a negative integer"); }
        if (task == null || explanation == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        if (explanation.isEmpty()) { throw new StringTooShortException(); }

        this.id = id;
        this.task = task;
        this.explanation = explanation;
        this.votedForId = votedForId;
        this.voterId = voterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("Argument cannot be a negative integer.");
        if (task.getVotes_list().stream().anyMatch(v -> v.getId() == id))
            throw new IllegalArgumentException("Vote with this id already exists.");
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        if (task == null) throw new IllegalArgumentException("Argument cannot be null.");
        this.task = task;
    }

    public String getExplanation(){
        return explanation;
    }

    public void setExplanation(String explanation) throws StringTooShortException {
        if (explanation == null) throw new IllegalArgumentException("Argument cannot be null");
        if (explanation.isEmpty()) throw new StringTooShortException();
        this.explanation = explanation;
    }

    public int getVotedForId() {
        return votedForId;
    }

    public void setVotedForId(int votedForId) {
        if (task.getTeamAssigned().getAssignees().stream().noneMatch(a -> a.getId() == votedForId))
            throw new IllegalArgumentException("No such Assignee with such ID found in the Task's Team.");
        this.votedForId = votedForId;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        if (task.getTeamAssigned().getAssignees().stream().noneMatch(a -> a.getId() == voterId))
            throw new IllegalArgumentException("No such Assignee with such ID found in the Task's Team.");
        this.voterId = voterId;
    }
}