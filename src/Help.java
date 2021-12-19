import ApplicationExceptions.StringTooShortException;

import java.util.Date;

public class Help {
    private int id;
    private Date date;
    private String description;
    private int assigneeId;
    private int pmId;

    private int taskId;

    public Help(int id, Date date, String description, int assigneeId, int pmId, int taskId) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.assigneeId = assigneeId;
        this.pmId = pmId;
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public int getPmId() {
        return pmId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setId(int id) {
        if(id<0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public void setDate(Date date) {
        if(date.before(new Date(System.currentTimeMillis()))) throw new IllegalArgumentException("date cannot be in the past");
        this.date = date;
    }

    public void setDescription(String description) throws StringTooShortException {
        if(description == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(description.length()<1) throw new StringTooShortException();
        else this.description = description;
    }

    public void setAssigneeId(int assigneeId) {
        if(assigneeId<0) throw new IllegalArgumentException("assigneeId cannot be a negative integer.");
        else this.assigneeId = assigneeId;
    }

    public void setPmId(int pmId) {
        if(pmId<0) throw new IllegalArgumentException("pmId cannot be a negative integer.");
        else this.pmId = pmId;
    }

    public void setTaskId(int taskId) {
        if(taskId<0) throw new IllegalArgumentException("taskId cannot be a negative integer.");
        else this.taskId = taskId;
    }
}