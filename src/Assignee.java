import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Assignee {
    //TODO implement id uniqueness
    private int id;
    private String firstName;
    private String lastName;
    private List<Task> tasks_list = new ArrayList<>();
    private List<Suggestion> suggestions_list = new ArrayList<>();
    private List<Reward> rewards_list = new ArrayList<>();

    public Assignee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Task> getTasks_list() {
        return tasks_list;
    }

    public void setTasks_list(List<Task> tasks_list) {
        this.tasks_list = tasks_list;
    }

    public List<Suggestion> getSuggestions_list() {
        return suggestions_list;
    }

    public void setSuggestions_list(List<Suggestion> suggestions_list) {
        this.suggestions_list = suggestions_list;
    }

    public List<Reward> getRewards_list() {
        return rewards_list;
    }

    public void setRewards_list(List<Reward> rewards_list) {
        this.rewards_list = rewards_list;
    }

    public void editProfile() {

    }

    //moved it here from Vote
    public Vote vote(int taskId, int id, String explanation, Assignee person) {
        //exception or smth
        //can't vote on a task if there is no task / it's not your task
        if (getTasks_list().isEmpty() || getTasks_list().get(taskId) == null) {
            return null;
        }
        Task task = getTasks_list().get(taskId);

        //exception or smth
        //can only cast vote if vote has been started
        if (!task.isVoteStarted()) {
            return null;
        }
        //id would be generated later?
        return new Vote(id, explanation, person, taskId, this.id);
    }

    //adding this made sense
    public Review review(int taskId, int id, boolean approved, String description) {
        //exception or smth
        //can't review their own task
        if (!(tasks_list.get(taskId) == null)) {
            return null;
        }
        //id would be generated later?
        return new Review(id, approved, description, taskId, this.id);
    }

    //adding this made sense too
    public Help requestHelp(int id, Date date, String description) {
        //id would be generated later?
        //TODO think of the way to get this assignee's pm
        //PM's id is the last required attribute (set to this.id temporarily)
        return new Help(id, date, description, this.id, this.id);
    }
}
