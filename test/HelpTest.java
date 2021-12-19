import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.*;

public class HelpTest {
    private int id;
    private Date date;
    private Date secDate;
    private Date thirdDate;
    private String description;
    private int assigneeId, pmId, taskId;
    private Help help;

    /**
     * All values are randomized for the sake of testing
     */
    @Before
    public void setUp() {
        id = 1;
        date = new Date();
        secDate = new Date();
        thirdDate = new Date(secDate.getTime() - 10);
        description = "Just for testing purposes";
        assigneeId = 2;
        pmId = 3;
        taskId = 2;
        help= new Help(id, date, description, assigneeId, pmId, taskId);

    }

    /**
     * Get the given values belonging to Help object
     * @result Help object values are returned accordingly for each getter
     * @corner_cases None
     */

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
     * @result Help object id is set anew and returned accordingly for each setter
     * @corner_case setId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void testSetId() {
        help.setId(3);
        assertEquals(3, help.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdIsInvalidException() {
        help.setId(-2);
    }

    /**
     * Set the given values to fields belonging to Help object
     * @result Help object date is set anew and returned accordingly for each setter
     * @corner_case setDate is tested on the matter of invalid input date - one before current date
     */

    @Test
    public void testSetDate() {
        help.setDate(secDate);
        assertEquals(secDate, help.getDate());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDateToEarlierThanCurrent() {
        help.setDate(thirdDate);
    }

    /**
     * Set the given values to fields belonging to Help object
     * @result Help object id is set anew and returned accordingly for each setter
     * @corner_case setDescription is tested on the matter of invalid input:
     *               1) Null
     *               2) Empty String
     */
    @Test
    public void testSetDescription() throws StringTooShortException {
        help.setDescription("New description");
        assertEquals("New description", help.getDescription());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDescriptionToNull() throws StringTooShortException {
        help.setDescription(null);
    }

    @Test(expected =StringTooShortException.class)
    public void testSetDescriptionToEmpty() throws StringTooShortException {
        help.setDescription("");
    }

    /**
     * Set the given values to fields belonging to Help object
     * @result Help object assigneeId is set anew and returned accordingly for each setter
     * @corner_case setAssigneeId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void testSetAssigneeId() {
        help.setAssigneeId(15);
        assertEquals(15, help.getAssigneeId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAssigneeIdToNegative() {
        help.setAssigneeId(-12);
    }

    /**
     * Set the given values to fields belonging to Help object
     * @result Help object pmId is set anew and returned accordingly for each setter
     * @corner_case setPmId is tested on the matter of invalid input id (negative argument)
     */
    @Test
    public void testSetPmId() {
        help.setPmId(42);
        assertEquals(42, help.getPmId());
    }

    @Test(expected =IllegalArgumentException.class)
    public void testSetPmIdToNegative() {
        help.setPmId(-12);
    }

    /**
     * Set the given values to fields belonging to Help object
     * @result Help object taskId is set anew and returned accordingly for each setter
     * @corner_case setTaskId is tested on the matter of invalid input id (negative argument)
     */
    @Test
    public void testSetTaskId() {
        help.setTaskId(12);
        assertEquals(12, help.getTaskId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTaskIdToNegative() {
        help.setTaskId(-12);
    }
}
