import ApplicationExceptions.*;

import java.util.*;

public class Assignee {
    private int id;
    private String firstName;
    private String lastName;
    private List<Task> tasks_list = new ArrayList<>();
    private List<Reward> rewards_list = new ArrayList<>();
    private HashSet<Suggestion> suggestions_list = new HashSet<>();
    private HashSet<Team> teams_list = new HashSet<>();

    public Assignee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        if(id<0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) throws StringTooShortException {
        if(firstName == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(firstName.length()<1) throw new StringTooShortException();
        else this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws StringTooShortException {
        if(lastName == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(lastName.length()<1) throw new StringTooShortException();
        else this.lastName = lastName;
    }


    public List<Task> getTasks_list() {
        return tasks_list;
    }

    public void setTasks_list(List<Task> tasks_list) {
        if(tasks_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.tasks_list = tasks_list;
    }

    public List<Reward> getRewards_list() {
        return rewards_list;
    }

    public void setRewards_list(List<Reward> rewards_list) {
        if(rewards_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.rewards_list = rewards_list;
    }

    public HashSet<Suggestion> getSuggestions_list() {
        return suggestions_list;
    }

    public void setSuggestions_list(HashSet<Suggestion> suggestions_list) {
        if(suggestions_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.suggestions_list = suggestions_list;
    }

    public HashSet<Team> getTeams_list() {
        return teams_list;
    }

    public void setTeams_list(HashSet<Team> teams_list) {
        if(teams_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.teams_list = teams_list;
    }

    public void editProfile() {
        System.out.println("Profile data saved!");
    }

    //moved it here from Vote
    //made it so that now votes are cast on tasks from a particular Team,
    //as tasks_list in Assignee is for Tasks currently assigned to that Assignee
    public Vote vote(int teamId, int taskId, int votedForId, String explanation) throws ApplicationExceptions.NoSuchTaskException, VoteNotStartedException, NoSuchTeamException, AlreadyVotedException {
        Optional<Team> teamOptional = teams_list.stream().filter(t -> (t.getId() == teamId)).findFirst();
        if(teamOptional.isEmpty()) {
            throw new NoSuchTeamException();
        }

        Team team = teamOptional.get();
        List<Task> tasks = team.getTasks();
        if (tasks.isEmpty() || tasks.stream().noneMatch(task -> task.getId() == taskId)) {
            throw new NoSuchTaskException();
        }

        Task task = tasks.stream().filter(t -> t.getId() == taskId).findFirst().get();
        if (!task.isVoteStarted()) {
            throw new VoteNotStartedException();
        }

        if(task.getVotes_list().stream().anyMatch(v -> v.getVoterId() == this.id)) {
            throw new AlreadyVotedException();
        }

        return new Vote(explanation, votedForId, this.id, task);
    }

    public Review review(int taskId, boolean approved, String description) throws NoSuchTaskException, CantReviewOwnTaskException {

        if (getTasks_list().stream().noneMatch(task -> task.getId() == taskId)) {
            throw new NoSuchTaskException();
        }

        //can't review their own task
        if (getTasks_list().stream().filter(task -> task.getId() == taskId).findFirst().get().getAssignees_list().contains(this)) {
            throw new CantReviewOwnTaskException();
        }

        return new Review(approved, description, this.id, taskId);
    }

    //adding this made sense too
    public Help requestHelp(Date date, String description, int taskId) {
        //id would be generated later? <- yeah
        int pmId = this.tasks_list.stream().filter(e -> e.getId() == taskId).findFirst().get().getTeamAssigned().getPM().getId();
        //PM's id is the last required attribute (set to this.id temporarily)
        return new Help(date, description, this.id, pmId, taskId);
    }

    public Suggestion createSuggestion(String name, String description) {
        return new Suggestion(name, description, this.id);
    }
}