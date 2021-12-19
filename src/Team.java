import java.util.ArrayList;
import java.util.List;

public class Team {
    private static int uniqueId = 0;
    private int id;
    private String name;
    private String description;

    private ProjectManager pm;
    private List<Assignee> assignees;
    private List<Task> tasks;

    public Team(String name, String description,
                ProjectManager pm, List<Assignee> assignees) {
        this.id = uniqueId;
        this.name = name;
        this.description = description;
        this.pm = pm;
        this.assignees = assignees;
        this.tasks = new ArrayList<>();
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
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

    public List<Task> getTasks(){
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void deleteTask(int task_id){
        this.tasks.removeIf(t -> (t.getId() == task_id));
    }
}
