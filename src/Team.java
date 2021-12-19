import java.util.List;

public class Team {
    private static int uniqueId = 0;
    private int id;
    private String name;
    private String description;

    private ProjectManager pm;
    private List<Assignee> assignees;

    public Team(String name, String description,
                ProjectManager pm, List<Assignee> assignees) {
        this.id = uniqueId;
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
