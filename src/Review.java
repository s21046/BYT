import ApplicationExceptions.StringTooShortException;

public class Review {
    private int id;
    private String description;
    private boolean approved;

    //TODO set up checks for these FKs in the {set} methods -> call proper exceptions -> add to tests
    private int assigneeId, taskId;

    public Review(String description, boolean approved, int assigneeId, int taskId) throws StringTooShortException {
        if ( assigneeId < 0 || taskId < 0) { throw new IllegalArgumentException("id cannot be a negative integer"); }
        if (description == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        if (description.length() < 15) { throw new StringTooShortException(); }

        this.approved = approved;
        this.description = description;
        this.assigneeId = assigneeId;
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws StringTooShortException {
        if (description == null) throw new IllegalArgumentException("Argument cannot be null");
        if (description.length() < 15) throw new StringTooShortException();
        else this.description = description;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        if (assigneeId < 0) throw new IllegalArgumentException("assigneeId cannot be a negative integer.");
        else this.assigneeId = assigneeId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        if (taskId < 0) throw new IllegalArgumentException("taskId cannot be a negative integer.");
        else this.taskId = taskId;
    }
}