public class Suggestion {
    private static int uniqueId = 0;
    private int id;
    private String name;
    private String description;
    private int assigneeId;

    public Suggestion(String name, String description, int assigneeId) {
        this.id = uniqueId++;
        this.name = name;
        this.description = description;
        this.assigneeId = assigneeId;
    }


    public int getId() {
        return id;
    }

    public static int getUniqueId() {
        return uniqueId;
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

    public static void setUniqueId(int uniqueId) {
        Suggestion.uniqueId = uniqueId;
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
