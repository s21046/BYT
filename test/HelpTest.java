import ApplicationExceptions.StringTooShortException;
import ApplicationExceptions.ValueAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static org.junit.Assert.assertEquals;

public class HelpTest {
    private int id;
    private LocalDate date, secDate, thirdDate;
    private String description;
    private int assigneeId, pmId, taskId;
    private Help help;
    UniqueIdGenerator<Help> uig;
    @Before
    public void setUp() throws StringTooShortException, ValueAlreadyExistsException {
        id = 0;
        uig = new UniqueIdGenerator<>();
        date = LocalDate.now();
        secDate = LocalDate.now();
        thirdDate = secDate.minus(10, ChronoUnit.DAYS);
        description = "Just for testing purposes";
        assigneeId = 2;
        pmId = 3;
        taskId = 2;
        help = new Help(uig, date, description, assigneeId, pmId, taskId);
    }

    /**
     * Initiate constructor with various values
     * Corner cases:
     * - values < 0 passed to the id field, null description/date, date from the past;
     * - empty description
     */

    @Test
    public void testConstructor() throws StringTooShortException, ValueAlreadyExistsException {
        new Help(uig, LocalDate.now(), "Help", 1, 1, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException, ValueAlreadyExistsException {
        new Help(uig, LocalDate.now().minus(10, ChronoUnit.DAYS), null, -1, -1, -1);
        new Help(uig, null, null, -1, -1, -1);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException, ValueAlreadyExistsException {
        new Help(uig, LocalDate.now(), "", 0, 0, 0);
    }

    /**
     * Get the given values belonging to Help object
     * Help object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(id, help.getId());
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

    /**
     * Set the {attribute} value of Help object
     * Help object {attribute} is set anew and returned accordingly for each setter
     * Corner cases:
     * - values < 0 passed to the id field, null description/date, date from the past;
     * - empty description
     */

    @Test
    public void testSetId() {
        assertEquals(0, help.getId());
        help.setId(3);
        assertEquals(3, help.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdIsInvalidException() {
        help.setId(-2);
    }

    @Test
    public void testSetDate() {
        assertEquals(date, help.getDate());
        help.setDate(secDate);
        assertEquals(secDate, help.getDate());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDateToNull() {
        help.setDate(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDateToEarlierThanCurrent() {
        help.setDate(thirdDate);
    }

    @Test
    public void testSetDescription() throws StringTooShortException {
        help.setDescription("New description");
        assertEquals("New description", help.getDescription());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDescriptionToNull() throws StringTooShortException {
        help.setDescription(null);
    }

    @Test(expected=StringTooShortException.class)
    public void testSetDescriptionToEmpty() throws StringTooShortException {
        help.setDescription("");
    }

    @Test
    public void testSetAssigneeId() {
        assertEquals(2, help.getAssigneeId());
        help.setAssigneeId(15);
        assertEquals(15, help.getAssigneeId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetAssigneeIdToNegative() {
        help.setAssigneeId(-12);
    }

    @Test
    public void testSetPmId() {
        assertEquals(3, help.getPmId());
        help.setPmId(42);
        assertEquals(42, help.getPmId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetPmIdToNegative() {
        help.setPmId(-12);
    }

    @Test
    public void testSetTaskId() {
        assertEquals(2, help.getTaskId());
        help.setTaskId(12);
        assertEquals(12, help.getTaskId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTaskIdToNegative() {
        help.setTaskId(-12);
    }
}
