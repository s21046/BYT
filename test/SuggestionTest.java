import ApplicationExceptions.StringTooShortException;
import ApplicationExceptions.ValueAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SuggestionTest {
    private int id;
    private String name;
    private String description;
    private int assigneeId;
    private Suggestion suggestion;
    UniqueIdGenerator<Suggestion> suggestionUniqueIdGenerator = new UniqueIdGenerator<>();
    @Before
    public void setUp() throws StringTooShortException, ValueAlreadyExistsException {
        id = 1;
        name = "Best suggestion";
        description = "I suggest making the world a better place.";
        assigneeId = 24;
        suggestion = new Suggestion( name, description, assigneeId);
        suggestion.setId(suggestionUniqueIdGenerator.generateId(suggestion));
    }

    /**
     * Initiate constructor with various values
     * Corner cases:
     * - values < 0 passed to the id field, null description/name
     * - empty description/name
     */

    @Test
    public void testConstructor() throws StringTooShortException {
        new Suggestion("Name", "BIIIIIIiiiiiigggggggg", 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException {
        new Suggestion(null, null, -1);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException {
        new Suggestion( "", "", 1);
    }

    /**
     * Get the given values belonging to Suggestion object
     * Suggestion object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(0, suggestion.getId());
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

    /**
     * Set the {attribute} value of Suggestion object
     * Suggestion object {attribute} is set anew and returned accordingly for each setter
     * Corner cases:
     * - values < 0 passed to the id field, null description/name
     * - empty description/name
     */

    @Test
    public void testSetId() {
        assertEquals(0, suggestion.getId());
        suggestion.setId(5);
        assertEquals(5, suggestion.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdToNegative() {
        suggestion.setId(-5);
    }

    @Test
    public void testSetName() throws StringTooShortException {
        assertEquals(name, suggestion.getName());
        suggestion.setName("newName");
        assertEquals("newName", suggestion.getName());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetNameToNull() throws StringTooShortException {
        suggestion.setName(null);
    }

    @Test(expected=StringTooShortException.class)
    public void testSetNameToEmpty() throws StringTooShortException {
        suggestion.setName("");
    }

    @Test
    public void testSetDescription() throws StringTooShortException {
        assertEquals(description, suggestion.getDescription());
        suggestion.setDescription("newDescription");
        assertEquals("newDescription", suggestion.getDescription());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDescriptionToNull() throws StringTooShortException {
        suggestion.setDescription(null);
    }

    @Test(expected=StringTooShortException.class)
    public void testSetDescriptionToEmpty() throws StringTooShortException {
        suggestion.setDescription("");
    }

    @Test
    public void testSetAssigneeId() {
        assertEquals(assigneeId, suggestion.getAssigneeId());
        suggestion.setAssigneeId(78);
        assertEquals(78, suggestion.getAssigneeId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetAssigneeIdToNegative() {
        suggestion.setAssigneeId(-5);
    }
}
