import ApplicationExceptions.StringTooShortException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
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

    public Task(int id, String name, String description, Date startDate, Date deadline, Status status, Team teamAssigned) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
        this.status = status;
        this.teamAssigned = teamAssigned;
        this.teamAssigned.addTask(this);
        this.voteStarted = false;
        this.votes_list = new ArrayList<>();
        this.assignees_list = new ArrayList<>();
        this.reviews_list = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getDeadline() {
        return deadline;
    }
    public Status getStatus() {
        return status;
    }
    public boolean isVoteStarted() {
        return voteStarted;
    }

    public Team getTeamAssigned(){return teamAssigned;}

    public List<Vote> getVotes_list() {
        return votes_list;
    }
    public List<Review> getReviews_list() {
        return reviews_list;
    }
    public List<Assignee> getAssignees_list() {
        return assignees_list;
    }

    public void setId(int id) {
        if(id<0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }
    public void setName(String name) throws StringTooShortException {
        if(name == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(name.length()<3) throw new StringTooShortException();
        else this.name = name;
    }
    public void setDescription(String description) throws StringTooShortException {
        if(description == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(description.length()<15) throw new StringTooShortException();
        else this.description = description;
    }
    public void setStartDate(Date startDate) {
        if(startDate == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (deadline.before(new Date(System.currentTimeMillis()))) throw new IllegalArgumentException("Start date cannot be in past.");
        if(startDate.after(deadline)) throw new IllegalArgumentException("Start date cannot be after the deadline");
        this.startDate = startDate;
    }
    public void setDeadline(Date deadline) {
        if(deadline == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (deadline.before(new Date(System.currentTimeMillis()))) throw new IllegalArgumentException("Deadline cannot be in past.");
        if(deadline.before(startDate)) throw new IllegalArgumentException("Deadline cannot precede the start date.");
        else this.deadline = deadline;
    }
    public void setStatus(Status status) {
        if(status == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.status = status;
    }
    public void setVoteStarted(boolean voteStarted) {
        this.voteStarted = voteStarted;
    }

    public void setTeamAssigned(Team teamAssigned){
        if(teamAssigned == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.teamAssigned = teamAssigned;
    }

    public void setVotes_list(List<Vote> votes_list) {
        if(votes_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.votes_list = votes_list;
    }
    public void setReviews_list(List<Review> reviews_list) {
        if(reviews_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.reviews_list = reviews_list;
    }
    public void setAssignees_list(List<Assignee> assignee_list) {
        if(assignee_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.assignees_list = assignee_list;
    }
}