import java.util.Date;

public class Help {
    //TODO implement id uniqueness
    private int id;
    private Date date;
    private String description;

    private int assigneeId, pmId;

    public Help(int id, Date date, String description,
                int assigneeId, int pmId) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.assigneeId = assigneeId;
        this.pmId = pmId;
    }
}
