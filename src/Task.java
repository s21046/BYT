import ApplicationExceptions.StringTooShortException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Task {
    private int id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate deadline;
    private Status status;
    private Team teamAssigned;

    private boolean voteStarted;

    private HashSet<Vote> votes_list = new HashSet<>();
    private HashSet<Review> reviews_list = new HashSet<>();
    private HashSet<Assignee> assignees_list = new HashSet<>();

    public Task(int id, String name, String description, LocalDate startDate, LocalDate endDate, LocalDate deadline, Status status, Team teamAssigned) throws StringTooShortException {
        if (id < 0) { throw new IllegalArgumentException("id cannot be a negative integer"); }
        if (description == null || name == null || startDate == null || deadline == null || status == null || teamAssigned == null)
        { throw new IllegalArgumentException("Argument cannot be null"); }
        if (description.length() < 15 || name.length() < 3) { throw new StringTooShortException(); }

        if (startDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Start date cannot be in past.");
        if (startDate.isAfter(deadline))
            throw new IllegalArgumentException("Start date cannot be after the deadline");

        if (endDate != null) {
            if (endDate.isBefore(startDate))
                throw new IllegalArgumentException("End date cannot precede the start date.");
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (startDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("Start date cannot be in past.");
        if (startDate.isAfter(deadline) || startDate.isAfter(endDate)) throw new IllegalArgumentException("Start date cannot be after the deadline/end date");
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("End date cannot be in past.");
        if (endDate.isBefore(startDate)) throw new IllegalArgumentException("End date cannot precede the start date.");
        this.endDate = endDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (deadline.isBefore(LocalDate.now())) throw new IllegalArgumentException("Deadline cannot be in past.");
        if (deadline.isBefore(startDate)) throw new IllegalArgumentException("Deadline cannot precede the start date.");
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

    public void setAssignees_list(HashSet<Assignee> assignees_list) {
        if (assignees_list == null) throw new IllegalArgumentException("Argument cannot be null");
        List<Assignee> team = new ArrayList<>(getTeamAssigned().getAssignees());
        team.add(getTeamAssigned().getPM());
        if (team.containsAll(assignees_list)) {
            this.assignees_list = assignees_list;
        } else throw new IllegalArgumentException("Assignees in the list should belong to this task's team.");
    }
}