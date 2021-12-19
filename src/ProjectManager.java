import ApplicationExceptions.IdAlreadyExistsException;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class ProjectManager extends Assignee {
    private HashSet<Help> helpRequests_list = new HashSet<>();

    public ProjectManager(int id, String firstName, String lastName) throws IdAlreadyExistsException {
        super(id, firstName, lastName);
    }

    public HashSet<Help> getHelpRequests_list() {
        return helpRequests_list;
    }

    public void setHelpRequests_list(HashSet<Help> helpRequests_list) {
        if(helpRequests_list == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.helpRequests_list = helpRequests_list;
    }

    public void giveReward(int rewardId, int assigneeId, String name, String description, RewardType type) throws IdAlreadyExistsException {
        Reward reward = new Reward(rewardId, name,description,type, Date.from(Instant.now()));
    }

    public Task createTask(int taskId, String name, String description, Date startDate,
                        Date deadline, Status status, Team team) throws IdAlreadyExistsException {
        return new Task(taskId, name, description, startDate, deadline, status, team);
    }

    public void deleteTask(int task_id, Team team) {
        team.deleteTask(task_id);
    }

    public void tiebreak(int teamId, int taskId, List<Assignee> assignee_list) {
        System.out.println("Tie broken!");
    }
}
