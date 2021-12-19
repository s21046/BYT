import java.util.Date;
import java.util.HashSet;

public class ProjectManager extends Assignee {
    private HashSet<Team> teams_list = new HashSet<>();
    private HashSet<Help> helpRequests_list = new HashSet<>();

    public ProjectManager(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public HashSet<Team> getTeams_list() {
        return teams_list;
    }

    public void setTeams_list(HashSet<Team> teams_list) {
        this.teams_list = teams_list;
    }

    public HashSet<Help> getHelpRequests_list() {
        return helpRequests_list;
    }

    public void setHelpRequests_list(HashSet<Help> helpRequests_list) {
        this.helpRequests_list = helpRequests_list;
    }

    public void giveReward(int assigneeId, int rewardId) {

    }

    public Task createTask(String name, String description, Date startDate,
                        Date deadline, Status status, boolean voteStarted, Team team) {
        return new Task(name, description, startDate, deadline, status, voteStarted, team);
    }

    public void deleteTask(int task_id, Team team) {
        team.deleteTask(task_id);
    }

    // This method is too general and doesn't specifies what to change, so I don't think we need it.
//    //TODO implement {only usable by PM} constraint
//    public Task updateTask(int id, String name, String description, Date startDate,
//                           Date deadline, Status status, boolean voteStarted) {
//        return null;
//    }

    //TODO implement {only usable by PM} constraint
    public void tiebreak() {

    }
}
