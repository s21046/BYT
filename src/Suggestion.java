public class Suggestion {
    private int id;
    private String name;
    private String description;
    private int assigneeId;

    public Suggestion(int id, String name, String description, int assigneeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assigneeId = assigneeId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }
}