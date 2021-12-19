import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SuggestionTest {
    private Suggestion suggestion;
    private int id;
    private String name;
    private String description;
    private int assigneeId;

    /**
     * All values are randomized for the sake of testing
     */

    @Before
    public void setUp() {
        id = 1;
        name = "Best suggestion";
        description = "I suggest make making world better place.";
        assigneeId = 24;
        suggestion = new Suggestion(id, name, description, assigneeId);
    }

    /**
     * Get the given values belonging to Suggestion object
     * @result Suggestion object values are returned accordingly for each getter
     * @corner_cases None
     */
    @Test
    public void testGetId() {assertEquals(id, suggestion.getId()); }

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
     * Set the given values to fields belonging to Suggestion object
     * @result Suggestion object id is set anew and returned accordingly for each setter
     * @corner_case setId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void testSetId() {
        int newId = 5;
        suggestion.setId(newId);
        assertEquals(newId, suggestion.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdToNegative() {
        int newId = -5;
        suggestion.setId(newId);
    }


    /**
     * Set the given values to fields belonging to Suggestion object
     * @result Suggestion object name is set anew and returned accordingly for each setter
     * @corner_case setName is tested on the matter of invalid input:
     *               1) Null
     *               2) Empty String
     */

    @Test
    public void testSetName() throws StringTooShortException {
        String newName = "A new name of the suggestion.";
        suggestion.setName(newName);
        assertEquals(newName, suggestion.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameToNull() throws StringTooShortException {
        suggestion.setName(null);
    }
    @Test(expected = StringTooShortException.class)
    public void testSetNameToEmpty() throws StringTooShortException {
        String newName = "";
        suggestion.setName(newName);
    }

    /**
     * Set the given values to fields belonging to Suggestion object
     * @result Suggestion object description is set anew and returned accordingly for each setter
     * @corner_case setDescription is tested on the matter of invalid input:
     *               1) Null
     *               2) Empty String
     */

    @Test
    public void testSetDescription() throws StringTooShortException {
        String newDescription = "This is new super description.";
        suggestion.setDescription(newDescription);
        assertEquals(newDescription, suggestion.getDescription());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDescriptionToNull() throws StringTooShortException {
        suggestion.setDescription(null);

    }

    @Test(expected = StringTooShortException.class)
    public void testSetDescriptionToEmpty() throws StringTooShortException {
        String newDescription = "";
        suggestion.setDescription(newDescription);
    }



    /**
     * Set the given values to fields belonging to Suggestion object
     * @result Suggestion object assigneeId is set anew and returned accordingly for each setter
     * @corner_case setAssigneeId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void testSetAssigneeId() {
        int newAssigneeId = 5;
        suggestion.setAssigneeId(newAssigneeId);
        assertEquals(newAssigneeId, suggestion.getAssigneeId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetAssigneeIdToNegative() {
        int newId = -5;
        suggestion.setAssigneeId(newId);
    }

}
