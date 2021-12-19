import ApplicationExceptions.IdAlreadyExistsException;
import ApplicationExceptions.StringTooShortException;

import java.util.HashSet;
import java.util.Set;

public class Review {
    private static final Set<Integer> ids = new HashSet<>();
    private int id;
    private String description;
    private int assigneeId;
    private int taskId;
    private boolean approved;

    public Review(int id, boolean approved, String description, int assigneeId, int taskId) throws IdAlreadyExistsException {
        if (!ids.add(id)) {
            throw new IdAlreadyExistsException();
        }
        this.id = id;
        this.approved = approved;
        this.description = description;
        this.assigneeId = assigneeId;
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public int getAssigneeId() {
        return assigneeId;
    }
    public int getTaskId() {
        return taskId;
    }
    public boolean isApproved() {
        return approved;
    }

    public void setId(int id) throws IdAlreadyExistsException {
        if (!ids.add(id)) {
            throw new IdAlreadyExistsException();
        }
        if(id<0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }
    public void setDescription(String description) throws StringTooShortException {
        if(description == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(description.length()<15) throw new StringTooShortException();
        else this.description = description;
    }
    public void setAssigneeId(int assigneeId) {
        if(assigneeId<0) throw new IllegalArgumentException("assigneeId cannot be a negative integer.");
        else this.assigneeId = assigneeId;
    }
    public void setTaskId(int taskId) {
        if(taskId<0) throw new IllegalArgumentException("taskId cannot be a negative integer.");
        else this.taskId = taskId;
    }
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
