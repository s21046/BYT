import ApplicationExceptions.*;

import java.util.*;

public class Assignee {
    private static int uniqueId = 0;
    private int id;
    private String firstName;
    private String lastName;
    private List<Task> tasks_list = new ArrayList<>();
    private List<Reward> rewards_list = new ArrayList<>();
    private HashSet<Suggestion> suggestions_list = new HashSet<>();
    private HashSet<Team> teams_list = new HashSet<>();

    public Assignee(String firstName, String lastName) {
        this.id = uniqueId++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public HashSet<Team> getTeams_list() {
        return teams_list;
    }

    public void setTeams_list(HashSet<Team> teams_list) {
        this.teams_list = teams_list;
    }

    public List<Task> getTasks_list() {
        return tasks_list;
    }

    public void setTasks_list(List<Task> tasks_list) {
        this.tasks_list = tasks_list;
    }

    public List<Reward> getRewards_list() {
        return rewards_list;
    }

    public void setRewards_list(List<Reward> rewards_list) {
        this.rewards_list = rewards_list;
    }

    public HashSet<Suggestion> getSuggestions_list() {
        return suggestions_list;
    }

    public void setSuggestions_list(HashSet<Suggestion> suggestions_list) {
        this.suggestions_list = suggestions_list;
    }

    public void editProfile() {
        System.out.println("Profile data saved!");
    }

    //moved it here from Vote
    //made it so that now votes are cast on tasks from a particular Team,
    //as tasks_list in Assignee is for Tasks currently assigned to that Assignee
    public Vote vote(int teamId, int taskId, String explanation) throws ApplicationExceptions.NoSuchTaskException, VoteNotStartedException, NoSuchTeamException, AlreadyVotedException {
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

        if(task.getVotes_list().stream().anyMatch(v -> v.getAssignee().equals(this))) {
            throw new AlreadyVotedException();
        }

        return new Vote(explanation, this, taskId);
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
        //id would be generated later?
        int pmId = this.tasks_list.stream().filter(e -> e.getId() == taskId).findFirst().get().getTeamAssigned().getPM().getId();
        //PM's id is the last required attribute (set to this.id temporarily)
        return new Help(date, description, this.id, pmId, taskId);
    }

    public Suggestion createSuggestion(String name, String description) {
        return new Suggestion(name, description, this.id);
    }
}
