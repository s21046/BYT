import java.util.Date;

public class Help {
    private static int uniqueId = 0;
    private int id;
    private Date date;
    private String description;

    private int assigneeId, pmId, taskId;

    public static int getUniqueId() {
        return uniqueId;
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

    public Help(Date date, String description,
                int assigneeId, int pmId, int taskId) {
        this.id = uniqueId++;
        this.date = date;
        this.description = description;
        this.assigneeId = assigneeId;
        this.pmId = pmId;
        this.taskId = taskId;
    }
}
