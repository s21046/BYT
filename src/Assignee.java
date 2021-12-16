import java.util.ArrayList;
import java.util.List;

public class Assignee {
    private int id;
    private String firstName;
    private String lastName;
    private List<Task> tasks_list = new ArrayList<>();

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
        if (!task.isVoteStarted()) {
            return null;
        }
        //id would be generated later
        return new Vote(id, explanation, person, taskId, this.id);
    }

    //adding this made sense
    public Review review(int taskId, int id, boolean approved, String description) {
        //exception or smth
        //can't review their own task
        if (!(tasks_list.get(taskId) == null)) {
            return null;
        }
        //id would be generated later
        return new Review(id, approved, description, taskId, this.id);
    }
}
