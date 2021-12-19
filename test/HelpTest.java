import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.*;

public class HelpTest {
    private static int uniqueId;
    private int id;
    private Date date;
    private Date secDate;
    private String description;
    private int assigneeId, pmId, taskId;
    private Help help;

    /**
     * All values are randomized except of uniqueId which is set to 1 because it is the first
     *  and only instance of Help object created for the sake of testing
     */
    @Before
    public void setUp() {
        uniqueId = 1;
        id = uniqueId;
        date = new Date();
        secDate = new Date();
        description = "Just for testing purposes";
        assigneeId = 2;
        pmId = 3;
        taskId = 2;
        help= new Help(date, description, assigneeId, pmId, taskId);

    }

    /**
     * Get the given values belonging to Help object
     * @result Help object values are returned accordingly for each getter
     * @warning getUniqueId is not tested as part of the Help instance but as a reference to Help.class
     */

    @Test
    public void testUniqueId() {
        assertEquals(uniqueId, Help.getUniqueId());
    }

    @Test
    public void testGetDate() {
        assertEquals(date, help.getDate());
    }

    @Test
    public void testGetDescription() {
        assertEquals(description, help.getDescription());
    }

    @Test
    public void testGetAssigneeId() {
        assertEquals(assigneeId, help.getAssigneeId());
    }

    @Test
    public void testPmId() {
        assertEquals(pmId, help.getPmId());
    }

    @Test
    public void testTaskId() {
        assertEquals(taskId, help.getTaskId());
    }

    @Test
    public void testGetId() {
        assertEquals(id, help.getId());
    }

    /**
     * Set the given values to fields belonging to Help object
     * @result Help object values are set anew and returned accordingly for each setter
     * @warning getUniqueId is not tested as part of the Help instance but as a reference to Help.class
     */

    @Test
    public void setUniqueId() {
        Help.setUniqueId(2);
        assertEquals(2, Help.getUniqueId());
    }

    @Test
    public void setId() {
        help.setId(3);
        assertEquals(3, help.getId());
    }

    @Test
    public void setDate() {

        help.setDate(secDate);
        assertEquals(secDate, help.getDate());
    }

    @Test
    public void setDescription() throws StringTooShortException {
        help.setDescription("New description");
        assertEquals("New description", help.getDescription());
    }

    @Test
    public void setAssigneeId() {
        help.setAssigneeId(15);
        assertEquals(15, help.getAssigneeId());
    }

    @Test
    public void setPmId() {
        help.setPmId(42);
        assertEquals(42, help.getPmId());
    }

    @Test
    public void setTaskId() {
        help.setTaskId(12);
        assertEquals(12, help.getTaskId());
    }
}
