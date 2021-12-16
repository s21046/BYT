import java.util.Date;

public class Task {
    //TODO implement id uniqueness
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date deadline;
    private Status status;
    private boolean voteStarted;

    //suggestions
    //private List<Vote> votes;
    //private List<review> reviews;
    //private Team team;

    public Task(int id, String name, String description, Date startDate,
                Date deadline, Status status, boolean voteStarted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
        this.status = status;
        this.voteStarted = voteStarted;
    }

    public boolean isVoteStarted() {
        return voteStarted;
    }

    //TODO implement {only usable by PM} constraint
    public Task createTask(int id, String name, String description, Date startDate,
                        Date deadline, Status status, boolean voteStarted) {
        return null;
    }

    //TODO implement {only usable by PM} constraint
    public void deleteTask() {

    }

    //TODO implement {only usable by PM} constraint
    public Task updateTask(int id, String name, String description, Date startDate,
                           Date deadline, Status status, boolean voteStarted) {
        return null;
    }

    //TODO implement {only usable by PM} constraint
    public void tiebreak() {

    }
}
