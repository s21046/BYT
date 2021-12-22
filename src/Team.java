import ApplicationExceptions.StringTooShortException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Team {
    private int id;
    private String name;
    private String description;

    private ProjectManager pm;
    private HashSet<Assignee> assignees;
    private HashSet<Task> tasks = new HashSet<>();

    public Team(int id, String name, String description, ProjectManager pm, HashSet<Assignee> assignees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pm = pm;
        this.assignees = assignees;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        if (id < 0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) throws StringTooShortException {
        if (name == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (name.length() < 3) throw new StringTooShortException();
        else this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) throws StringTooShortException {
        if (description == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (description.length() < 15) throw new StringTooShortException();
        else this.description = description;
    }

    public ProjectManager getPM() {
        return pm;
    }

    public void setPM(ProjectManager pm) {
        if (pm == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.pm = pm;
    }

    public HashSet<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(HashSet<Assignee> assignees) {
        if (assignees == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.assignees = assignees;
    }

    public HashSet<Task> getTasks(){
        return tasks;
    }

    public void setTasks(HashSet<Task> tasks) {
        if (tasks == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.tasks = tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void deleteTask(int task_id){
        this.tasks.removeIf(t -> (t.getId() == task_id));
    }
}