import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ReviewTest {
    private int id;
    private String description;
    private int assigneeId;
    private int taskId;
    private boolean approved;
    private Task task;
    private Date day1;
    private Date day2;
    private Status status;
    private Team team;
    private ProjectManager pm;
    private Review rev;

    @Before
    public void setUp(){
        id = 5;
        description = "Quack";
        assigneeId = 1;
        taskId = 2;
        approved = true;
        status = Status.ASSIGNED;
        pm = new ProjectManager(2,"Jerycho", "Swain");
        team = new Team(1,"Birbs", "Focus on testing", pm, null);
        day1 = new Date();
        day2 = new Date();
        task = new Task(1, "Some task", "Nothing", day1, day2, status, team);
        rev = new Review(id, approved, description, assigneeId, taskId);
    }

    /**
     * Get the given values belonging to Review object
     * @result Review object values are returned accordingly for each getter
     * @corner_cases None
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
    public void testGetAssigneeId() {
        assertEquals(assigneeId, rev.getAssigneeId());
    }

    @Test
    public void testGetTaskId() {
        assertEquals(taskId, rev.getTaskId());
    }

    @Test
    public void testIsApproved(){
        assertEquals(approved, rev.isApproved());
    }


    /**
     * Set the given values to fields belonging to Review object
     * @result Review object values are set anew and returned accordingly for each setter
     * @corner_cases setId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void testSetId() {
        rev.setId(2);
        assertEquals(2, rev.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdToNegative() {
        rev.setId(-2);
    }


    /**
     * Set the given values to fields belonging to Help object
     * @result Help object id is set anew and returned accordingly for each setter
     * @corner_case setDescription is tested on the matter of invalid input:
     *               1) Null
     *               2) Empty String
     */

    @Test
    public void setDescription() throws StringTooShortException {
        rev.setDescription("New description");
        assertEquals("New description", rev.getDescription());
    }

    @Test(expected=IllegalArgumentException.class)
    public void setDescriptionToNull() throws StringTooShortException {
        rev.setDescription(null);
    }

    @Test(expected=StringTooShortException.class)
    public void setDescriptionToEmpty() throws StringTooShortException {
        rev.setDescription("");
    }


    /**
     * Set the given values to fields belonging to Review object
     * @result Review object values are set anew and returned accordingly for each setter
     * @corner_cases setAssigneeId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void setAssigneeId() {
        rev.setAssigneeId(2);
        assertEquals(2, rev.getAssigneeId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetAssigneeIdToNegative() {
        rev.setAssigneeId(-2);
    }

    /**
     * Set the given values to fields belonging to Review object
     * @result Review object values are set anew and returned accordingly for each setter
     * @corner_cases setTaskId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void setTaskId() {
        rev.setTaskId(2);
        assertEquals(2, rev.getTaskId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void setTaskIdToNegative() {
        rev.setTaskId(-2);
    }


    /**
     * Set the given values to fields belonging to Review object
     * @result Review object value approved is set anew and returned
     */

    @Test
    public void setApproved() {
        rev.setApproved(false);
        assertFalse(rev.isApproved());
    }
}