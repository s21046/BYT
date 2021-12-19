import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reward {
    private static int uniqueId = 0;
    private int id;
    private String name;
    private String description;
    private RewardType type;
    private Date dateGiven;

    private List<Assignee> assignees_list = new ArrayList<>();

    public Reward(String name, String description, RewardType type, Date dateGiven) {
        this.id = uniqueId++;
        this.name = name;
        this.description = description;
        this.type = type;
        this.dateGiven = dateGiven;
    }

    public List<Assignee> getAssignees_list() {
        return assignees_list;
    }

    public void setAssignees_list(List<Assignee> assignees_list) {
        this.assignees_list = assignees_list;
    }
}
