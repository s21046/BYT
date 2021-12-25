import ApplicationExceptions.StringTooShortException;
import ApplicationExceptions.ValueAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ReviewTest {
    private String description;
    private boolean approved;
    private int assigneeId, taskId;
    private Task task;
    private LocalDate day1, day2;
    private Status status;
    private Team team;
    private ProjectManager pm;
    private Review rev;

    UniqueIdGenerator<Assignee> assigneeUniqueIdGenerator;
    UniqueIdGenerator<Team> teamUniqueIdGenerator;
    UniqueIdGenerator<Reward> rewardUniqueIdGenerator;
    UniqueIdGenerator<Task> taskUniqueIdGenerator;
    UniqueIdGenerator<Suggestion> suggestionUniqueIdGenerator;
    UniqueIdGenerator<Review> reviewUniqueIdGenerator;
    @Before
    public void setUp() throws StringTooShortException, ValueAlreadyExistsException {
        assigneeUniqueIdGenerator = new UniqueIdGenerator<>();
        teamUniqueIdGenerator = new UniqueIdGenerator<>();
        rewardUniqueIdGenerator = new UniqueIdGenerator<>();
        taskUniqueIdGenerator = new UniqueIdGenerator<>();
        suggestionUniqueIdGenerator = new UniqueIdGenerator<>();
        reviewUniqueIdGenerator = new UniqueIdGenerator<>();
        description = "Quack-quack-quack-quack";
        assigneeId = 1;
        taskId = 2;
        approved = true;
        status = Status.ASSIGNED;
        pm = new ProjectManager("Jerycho", "Swain");
        pm.setId(assigneeUniqueIdGenerator.generateId(pm));

        team = new Team("Birbs", "Focus on testing pls rn", pm, new HashSet<>());
        day1 = LocalDate.now();
        day2 = LocalDate.now();
        task = new Task( "Some task", "Nothing, relaxxxxxxx", day1, null, day2, status, team);
        task.setId(taskUniqueIdGenerator.generateId(task));
        rev = new Review( description, approved, assigneeId, taskId);
        rev.setId(reviewUniqueIdGenerator.generateId(rev));
    }

    /**
     * Initiate constructor with various values
     * Corner cases:
     * - values < 0 passed to the id field, null description;
     * - description.length < 15
     */

    @Test
    public void testConstructor() throws StringTooShortException {
        new Review("BIIIIiiiiiiiig Description", true, 1, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException {
        new Review( null, false, -1, -1);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException {
        new Review( "short", true, 0, 0);
    }

    /**
     * Get the given values belonging to Review object
     * Review object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(0, rev.getId());
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
        assertEquals(0, rev.getId());
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