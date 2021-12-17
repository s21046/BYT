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
}
