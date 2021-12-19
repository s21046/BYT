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
}
