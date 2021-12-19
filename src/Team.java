import ApplicationExceptions.IdAlreadyExistsException;
import ApplicationExceptions.StringTooShortException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team {
    private static final Set<Integer> ids = new HashSet<>();
    private int id;
    private String name;
    private String description;

    private ProjectManager pm;
    private List<Assignee> assignees;
    private List<Task> tasks;

    public Team(int id, String name, String description, ProjectManager pm, List<Assignee> assignees) throws IdAlreadyExistsException {
        if (!ids.add(id)) {
            throw new IdAlreadyExistsException();
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.pm = pm;
        this.assignees = assignees;
        this.tasks = new ArrayList<>();
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public ProjectManager getPM() {
        return pm;
    }
    public List<Assignee> getAssignees() {
        return assignees;
    }
    public List<Task> getTasks(){
        return tasks;
    }

    public void setId(int id) throws IdAlreadyExistsException {
        if (!ids.add(id)) {
            throw new IdAlreadyExistsException();
        }
        if(id<0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }
    public void setName(String name) throws StringTooShortException {
        if(name == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(name.length()<3) throw new StringTooShortException();
        else this.name = name;
    }
    public void setDescription(String description) throws StringTooShortException {
        if(description == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(description.length()<15) throw new StringTooShortException();
        else this.description = description;
    }
    public void setPM(ProjectManager pm) {
        if(pm == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.pm = pm;
    }
    public void setAssignees(List<Assignee> assignees) {
        if(assignees == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.assignees = assignees;
    }
    public void setTasks(List<Task> tasks) {
        if(tasks == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.tasks = tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }
    public void deleteTask(int task_id){
        this.tasks.removeIf(t -> (t.getId() == task_id));
    }
}
