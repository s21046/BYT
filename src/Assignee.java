import ApplicationExceptions.*;

import java.time.LocalDate;
import java.util.*;

public class Assignee {
    private int id;
    private String firstName;
    private String lastName;

    private HashSet<Task> tasks_list = new HashSet<>();
    private List<Reward> rewards_list = new ArrayList<>();
    private HashSet<Suggestion> suggestions_list = new HashSet<>();
    private HashSet<Team> teams_list = new HashSet<>();

    public Assignee(String firstName, String lastName) throws StringTooShortException {
        if (firstName == null || lastName == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        if (firstName.isEmpty() || lastName.isEmpty()) { throw new StringTooShortException(); }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        if (id < 0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) throws StringTooShortException {
        if (firstName == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (firstName.isEmpty()) throw new StringTooShortException();
        else this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws StringTooShortException {
        if (lastName == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (lastName.isEmpty()) throw new StringTooShortException();
        else this.lastName = lastName;
    }

    public HashSet<Task> getTasks_list() {
        return tasks_list;
    }

    public void setTasks_list(HashSet<Task> tasks_list) {
        if (tasks_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.tasks_list = tasks_list;
    }

    public List<Reward> getRewards_list() {
        return rewards_list;
    }

    public void setRewards_list(List<Reward> rewards_list) {
        if (rewards_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.rewards_list = rewards_list;
    }

    public HashSet<Suggestion> getSuggestions_list() {
        return suggestions_list;
    }

    public void setSuggestions_list(HashSet<Suggestion> suggestions_list) {
        if (suggestions_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.suggestions_list = suggestions_list;
    }

    public HashSet<Team> getTeams_list() {
        return teams_list;
    }

    public void setTeams_list(HashSet<Team> teams_list) {
        if (teams_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.teams_list = teams_list;
    }

    public void editProfile() {
        System.out.println("Profile data saved!");
    }

    //votes are cast on tasks from a particular Team,
    //as tasks_list in Assignee is for Tasks currently assigned to that Assignee
    public Vote vote(int voteId, int teamId, int taskId, int votedForId, String explanation)
            throws NoSuchTaskException, VoteNotStartedException, NoSuchTeamException, AlreadyVotedException, StringTooShortException {

        Team team = getTeams_list().stream().filter(t -> (t.getId() == teamId)).findFirst().orElse(null);
        if (team == null) { throw new NoSuchTeamException(); }

        HashSet<Task> tasks = team.getTasks();
        Task task = tasks.stream().filter(t -> t.getId() == taskId).findFirst().orElse(null);

        if (tasks.isEmpty() || task == null) { throw new NoSuchTaskException(); }
        if (!task.isVoteStarted()) { throw new VoteNotStartedException(); }
        if (task.getVotes_list().stream().anyMatch(v -> v.getVoterId() == this.id)) {
            throw new AlreadyVotedException();
        }

        return new Vote( task, explanation, votedForId, this.id);
    }

    public Review review(int reviewId, int taskId, boolean approved, String description)
            throws NoSuchTaskException, CantReviewOwnTaskException, StringTooShortException {

        Task task = getTasks_list().stream().filter(t -> t.getId() == taskId).findFirst().orElse(null);
        if (task == null) { throw new NoSuchTaskException(); }
        if (task.getAssignees_list().contains(this)) {
            throw new CantReviewOwnTaskException();
        }

        return new Review( description, approved, this.id, taskId);
    }

    public Help requestHelp(int helpId, LocalDate date, String description, int taskId) throws StringTooShortException {
        int pmId = this.tasks_list.stream().filter(e -> e.getId() == taskId).findFirst().get()
                .getTeamAssigned().getPM().getId();
        return new Help( date, description, this.id, pmId, taskId);
    }

    public Suggestion createSuggestion(int suggestId, String name, String description) throws StringTooShortException {
        return new Suggestion( name, description, this.id);
    }
}