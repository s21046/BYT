import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ReviewTest {
    private int id;
    private String description;
    private boolean approved;
    private int assigneeId, taskId;
    private Task task;
    private Date day1, day2;
    private Status status;
    private Team team;
    private ProjectManager pm;
    private Review rev;

    @Before
    public void setUp() throws StringTooShortException {
        id = 5;
        description = "Quack-quack-quack-quack";
        assigneeId = 1;
        taskId = 2;
        approved = true;
        status = Status.ASSIGNED;
        pm = new ProjectManager(2,"Jerycho", "Swain");
        team = new Team(1,"Birbs", "Focus on testing", pm, null);
        day1 = new Date();
        day2 = new Date();
        task = new Task(1, "Some task", "Nothing", day1, null, day2, status, team);
        rev = new Review(id, description, approved, assigneeId, taskId);
    }

    /**
     * Initiate constructor with various values
     * Corner cases:
     * - values < 0 passed to the id field, null description;
     * - description.length < 15
     */

    @Test
    public void testConstructor() throws StringTooShortException {
        new Review(1, "BIIIIiiiiiiiig Description", true, 1, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException {
        new Review(-1, null, false, -1, -1);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException {
        new Review(0, "short", true, 0, 0);
    }

    /**
     * Get the given values belonging to Review object
     * Review object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(id, rev.getId());
    }

    @Test
    public void testGetDescription() {
        assertEquals(description, rev.getDescription());
    }

    @Test
    public void testIsApproved(){
        assertEquals(approved, rev.isApproved());
    }

    @Test
    public void testGetAssigneeId() {
        assertEquals(assigneeId, rev.getAssigneeId());
    }

    @Test
    public void testGetTaskId() {
        assertEquals(taskId, rev.getTaskId());
    }

    /**
     * Set the {attribute} value of Review object
     * Review object {attribute} is set anew and returned accordingly for each setter
     * Corner cases:
     * - values < 0 passed to the id field, null description;
     * - description.length < 15
     */

    @Test
    public void testSetId() {
        assertEquals(5, rev.getId());
        rev.setId(2);
        assertEquals(2, rev.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdToNegative() {
        rev.setId(-2);
    }

    @Test
    public void testSetDescription() throws StringTooShortException {
        assertEquals("Quack-quack-quack-quack", rev.getDescription());
        rev.setDescription("New description");
        assertEquals("New description", rev.getDescription());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDescriptionToNull() throws StringTooShortException {
        rev.setDescription(null);
    }

    @Test(expected=StringTooShortException.class)
    public void testSetDescriptionStringTooShort() throws StringTooShortException {
        rev.setDescription("Descr");
    }

    @Test
    public void testSetApproved() {
        assertTrue(rev.isApproved());
        rev.setApproved(false);
        assertFalse(rev.isApproved());
    }

    @Test
    public void testSetAssigneeId() {
        assertEquals(1, rev.getAssigneeId());
        rev.setAssigneeId(2);
        assertEquals(2, rev.getAssigneeId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetAssigneeIdToNegative() {
        rev.setAssigneeId(-2);
    }

    @Test
    public void testSetTaskId() {
        assertEquals(2, rev.getTaskId());
        rev.setTaskId(2);
        assertEquals(2, rev.getTaskId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTaskIdToNegative() {
        rev.setTaskId(-2);
    }
}