import ApplicationExceptions.CantReviewOwnTaskException;
import ApplicationExceptions.NoSuchTaskException;
import ApplicationExceptions.VoteNotStartedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Assignee {
    private static int uniqueId = 0;
    private int id;
    private String firstName;
    private String lastName;
    private List<Task> tasks_list = new ArrayList<>();
    private List<Reward> rewards_list = new ArrayList<>();

    private HashSet<Suggestion> suggestions_list = new HashSet<>();

    public Assignee(String firstName, String lastName) {
        this.id = uniqueId++;
        this.firstName = firstName;
        this.lastName = lastName;
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

    }

    //moved it here from Vote
    public Vote vote(int taskId, String explanation) throws ApplicationExceptions.NoSuchTaskException, VoteNotStartedException {
        if (getTasks_list().isEmpty() || getTasks_list().stream().noneMatch(task -> task.getId() == taskId)) {
            throw new NoSuchTaskException();
        }

        if (!getTasks_list().stream().filter(task -> task.getId() == taskId).findFirst().get().isVoteStarted()) {
            throw new VoteNotStartedException();
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
    public Help requestHelp(Date date, String description) {
        //id would be generated later?
        //TODO think of the way to get this assignee's pm
        //PM's id is the last required attribute (set to this.id temporarily)
        return new Help(date, description, this.id, this.id);
    }

    public Suggestion createSuggestion(String name, String description) {
        return new Suggestion(name, description, this.id);
    }
}
