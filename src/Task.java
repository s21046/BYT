import java.util.Date;
import java.util.List;

public class Task {
    private static int uniqueId = 0;
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date deadline;
    private Status status;
    private boolean voteStarted;

    private Team teamAssigned;

    private List<Vote> votes_list;
    private List<Review> reviews_list;
    private List<Assignee> assignees_list;

    public Task(String name, String description, Date startDate,
                Date deadline, Status status, boolean voteStarted) {
        this.id = uniqueId++;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
        this.status = status;
        this.voteStarted = voteStarted;
    }

    public List<Vote> getVotes_list() {
        return votes_list;
    }

    public void setVotes_list(List<Vote> votes_list) {
        this.votes_list = votes_list;
    }

    public List<Review> getReviews_list() {
        return reviews_list;
    }

    public void setReviews_list(List<Review> reviews_list) {
        this.reviews_list = reviews_list;
    }

    public List<Assignee> getAssignees_list() {
        return assignees_list;
    }

    public void setAssignees_list(List<Assignee> assignee_list) {
        this.assignees_list = assignee_list;
    }

    public Team getTeamAssigned(){return teamAssigned;}

    public void setTeamAssigned(Team teamAssigned){this.teamAssigned = teamAssigned;}

    public boolean isVoteStarted() {
        return voteStarted;
    }

// Moved these methods to PM class as they make more sense there
//    //TODO implement {only usable by PM} constraint
//    public Task createTask(int id, String name, String description, Date startDate,
//                        Date deadline, Status status, boolean voteStarted) {
//        return null;
//    }
//
//    //TODO implement {only usable by PM} constraint
//    public void deleteTask() {
//
//    }
//
//    //TODO implement {only usable by PM} constraint
//    public Task updateTask(int id, String name, String description, Date startDate,
//                           Date deadline, Status status, boolean voteStarted) {
//        return null;
//    }
//
//    //TODO implement {only usable by PM} constraint
//    public void tiebreak() {
//
//    }

    public int getId() {
        return id;
    }
}
