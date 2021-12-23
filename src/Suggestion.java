import ApplicationExceptions.StringTooShortException;

public class Suggestion {
    private int id;
    private String name;
    private String description;

    //TODO set up checks for these FKs in the {set} methods -> call proper exceptions -> add to tests
    private int assigneeId;

    public Suggestion(int id, String name, String description, int assigneeId) throws StringTooShortException {
        if (id < 0 || assigneeId < 0) { throw new IllegalArgumentException("id cannot be a negative integer"); }
        if (description == null || name == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        if (description.isEmpty() || name.isEmpty()) { throw new StringTooShortException(); }

        this.id = id;
        this.name = name;
        this.description = description;
        this.assigneeId = assigneeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws StringTooShortException {
        if (name == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (name.isEmpty()) throw new StringTooShortException();
        else this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws StringTooShortException {
        if (description == null) throw new IllegalArgumentException("Argument cannot be null");
        else if (description.isEmpty()) throw new StringTooShortException();
        else this.description = description;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        if (assigneeId < 0) throw new IllegalArgumentException("assigneeId cannot be a negative integer.");
        else this.assigneeId = assigneeId;
    }
}