import java.util.HashSet;

public class ProjectManager extends Assignee {
    private HashSet<Team> teams_list = new HashSet<>();
    private HashSet<Help> helpRequests_list = new HashSet<>();

    public ProjectManager(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
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
}
