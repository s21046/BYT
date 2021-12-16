import java.util.List;

public class Team {
    //TODO implement id uniqueness
    private int id;
    private String name;
    private String description;

    private ProjectManager pm;
    private List<Assignee> assignees;

    public Team(int id, String name, String description,
                ProjectManager pm, List<Assignee> assignees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pm = pm;
        this.assignees = assignees;
    }

    public ProjectManager getPM() {
        return pm;
    }

    public void setPM(ProjectManager pm) {
        this.pm = pm;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }
}
