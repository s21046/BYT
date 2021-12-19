import org.junit.Before;
import org.junit.Test;

public class SuggestionTest {
    private Suggestion suggestion;
    private int id;
    private String name;
    private String description;
    private int assigneeId;

    @Before
    public void setUp() {
        id = 1;
        name = "Best suggestion";
        description = "I suggest make making world better place.";
        assigneeId = 24;
        suggestion = new Suggestion(id, name, description, assigneeId);
    }

    @Test
    public void testGetId() {
        assertEqueals(id, suggestion.getId());
    }

    @Test
    public void testGetName() {
        assertEquals(name, suggestion.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals(description, suggestion.getDescription());
    }

    @Test
    public void testGetAssigneeId() {
        assertEquals(assigneeId, suggestion.getAssigneeId());
    }

    @Test
    public void testSetId() {
        int newId = 5;
        suggestion.setId(newId);
        assertEqueals(newId, suggestion.getId());
    }

    @Test
    public void testSetName() {
        String newName = "A new name of the suggestion.";
        suggestion.setName(newName);
        assertEqueals(newName, suggestion.getName());
    }

    @Test
    public void testSetDescription() {
        String newDescription = "This is new super description.";
        suggestion.setDescription(newDescription);
        assertEqueals(newDescription, suggestion.getDescription());
    }

    @Test
    public void testSetAssigneeId() {
        int newAssigneeId = 5;
        suggestion.setAssigneeId(newAssigneeId);
        assertEqueals(newAssigneeId, suggestion.getAssigneeId());
    }
}
