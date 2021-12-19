import java.util.Date;

public class Help {
    private static int uniqueId = 0;
    private int id;
    private Date date;
    private String description;
    private int assigneeId;
    private int pmId;


    private int taskId;

    public Help(Date date, String description,
                int assigneeId, int pmId, int taskId) {
        this.id = uniqueId++;
        this.date = date;
        this.description = description;
        this.assigneeId = assigneeId;
        this.pmId = pmId;
        this.taskId = taskId;
    }

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

    public static void setUniqueId(int uniqueId) {
        Help.uniqueId = uniqueId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public void setPmId(int pmId) {
        this.pmId = pmId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
