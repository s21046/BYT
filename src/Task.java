import ApplicationExceptions.StringTooShortException;

import java.util.Date;
import java.util.HashSet;

public class Task {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date deadline;
    private Status status;
    private Team teamAssigned;

    private boolean voteStarted;

    private HashSet<Vote> votes_list = new HashSet<>();
    private HashSet<Review> reviews_list = new HashSet<>();
    private HashSet<Assignee> assignees_list = new HashSet<>();

    public Task(int id, String name, String description, Date startDate, Date endDate, Date deadline, Status status, Team teamAssigned) throws StringTooShortException {
        if (id < 0) { throw new IllegalArgumentException("id cannot be a negative integer"); }
        if (description == null || name == null || startDate == null || deadline == null || status == null || teamAssigned == null)
        { throw new IllegalArgumentException("Argument cannot be null"); }
        if (description.length() < 15 || name.length() < 3) { throw new StringTooShortException(); }

        if (startDate.before(new Date(System.currentTimeMillis())))
            throw new IllegalArgumentException("Start date cannot be in past.");
        if (startDate.after(deadline))
            throw new IllegalArgumentException("Start date cannot be after the deadline");
        if (deadline.before(new Date(System.currentTimeMillis())))
            throw new IllegalArgumentException("Deadline cannot be in past.");
        if (deadline.before(startDate))
            throw new IllegalArgumentException("Deadline cannot precede the start date.");

        if (endDate != null) {
            if (endDate.before(new Date(System.currentTimeMillis())))
                throw new IllegalArgumentException("End date cannot be in past.");
            if (endDate.before(startDate))
                throw new IllegalArgumentException("End date cannot precede the start date.");
            if (startDate.after(endDate))
                throw new IllegalArgumentException("Start date cannot be after the end date");
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadline = deadline;
        this.status = status;
        this.teamAssigned = teamAssigned;
        this.teamAssigned.addTask(this);
        this.voteStarted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws StringTooShortException {
        if (name == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (name.length() < 3) throw new StringTooShortException();
        else this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws StringTooShortException {
        if (description == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (description.length() < 15) throw new StringTooShortException();
        else this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        if (startDate == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (startDate.before(new Date(System.currentTimeMillis()))) throw new IllegalArgumentException("Start date cannot be in past.");
        if (startDate.after(deadline) || startDate.after(endDate)) throw new IllegalArgumentException("Start date cannot be after the deadline/end date");
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        if (endDate.before(new Date(System.currentTimeMillis()))) throw new IllegalArgumentException("End date cannot be in past.");
        if (endDate.before(startDate)) throw new IllegalArgumentException("End date cannot precede the start date.");
        this.endDate = endDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        if (deadline == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (deadline.before(new Date(System.currentTimeMillis()))) throw new IllegalArgumentException("Deadline cannot be in past.");
        if (deadline.before(startDate)) throw new IllegalArgumentException("Deadline cannot precede the start date.");
        else this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.status = status;
    }

    public Team getTeamAssigned() {
        return teamAssigned;
    }

    public void setTeamAssigned(Team teamAssigned) {
        if (teamAssigned == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.teamAssigned = teamAssigned;
    }

    public boolean isVoteStarted() {
        return voteStarted;
    }

    public void setVoteStarted(boolean voteStarted) {
        this.voteStarted = voteStarted;
    }

    public HashSet<Vote> getVotes_list() {
        return votes_list;
    }

    public void setVotes_list(HashSet<Vote> votes_list) {
        if (votes_list == null) throw new IllegalArgumentException("Argument cannot be null");
        if (votes_list.stream().allMatch(v -> v.getTask().getId() == this.id)) {
            this.votes_list = votes_list;
        } else throw new IllegalArgumentException("All votes in the list should belong to this task.");
    }

    public HashSet<Review> getReviews_list() {
        return reviews_list;
    }

    public void setReviews_list(HashSet<Review> reviews_list) {
        if (reviews_list == null) throw new IllegalArgumentException("Argument cannot be null");
        if (reviews_list.stream().allMatch(r -> r.getTaskId() == this.id)) {
            this.reviews_list = reviews_list;
        } else throw new IllegalArgumentException("All reviews in the list should belong to this task.");
    }

    public HashSet<Assignee> getAssignees_list() {
        return assignees_list;
    }

    public void setAssignees_list(HashSet<Assignee> assignee_list) {
        if (assignee_list == null) throw new IllegalArgumentException("Argument cannot be null");
        if (assignee_list.stream().allMatch(
                a -> a.getTeams_list().stream().anyMatch(
                        t -> t.getId() == this.teamAssigned.getId()))) {
            this.assignees_list = assignee_list;
        } else throw new IllegalArgumentException("Assignees in the list should belong to this task's team.");
    }
}