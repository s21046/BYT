import java.util.Date;

public class Help {
    private static int uniqueId = 0;
    private int id;
    private Date date;
    private String description;

    private int assigneeId, pmId;

    public Help(Date date, String description,
                int assigneeId, int pmId) {
        this.id = uniqueId++;
        this.date = date;
        this.description = description;
        this.assigneeId = assigneeId;
        this.pmId = pmId;
    }
}
