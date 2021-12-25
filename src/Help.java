import ApplicationExceptions.StringTooShortException;

import java.time.LocalDate;

public class Help {
    private int id;
    private LocalDate date;
    private String description;

    //TODO set up checks for these FKs in the {set} methods -> call proper exceptions -> add to tests

    private int assigneeId, pmId, taskId;

    public Help(LocalDate date, String description, int assigneeId, int pmId, int taskId) throws StringTooShortException {
        if (assigneeId < 0 || pmId < 0 || taskId < 0) { throw new IllegalArgumentException("id cannot be a negative integer"); }
        if (description == null || date == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        if (description.isEmpty()) { throw new StringTooShortException(); }
        if (date.isBefore(LocalDate.now())) throw new IllegalArgumentException("date cannot be in the past");

        this.date = date;
        this.description = description;
        this.assigneeId = assigneeId;
        this.pmId = pmId;
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Argument cannot be null");
        if (date.isBefore(LocalDate.now())) throw new IllegalArgumentException("date cannot be in the past");
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws StringTooShortException {
        if (description == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (description.isEmpty()) throw new StringTooShortException();
        else this.description = description;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        if (assigneeId < 0) throw new IllegalArgumentException("assigneeId cannot be a negative integer.");
        else this.assigneeId = assigneeId;
    }

    public int getPmId() {
        return pmId;
    }

    public void setPmId(int pmId) {
        if (pmId < 0) throw new IllegalArgumentException("pmId cannot be a negative integer.");
        else this.pmId = pmId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        if (taskId < 0) throw new IllegalArgumentException("taskId cannot be a negative integer.");
        else this.taskId = taskId;
    }
}