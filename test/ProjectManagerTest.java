import ApplicationExceptions.StringTooShortException;
import ApplicationExceptions.ValueAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * This test class covers only methods unique for ProjectManager.
 * For tests for all methods it inherits from Assignee refer to AssigneeTest.java
 */

public class ProjectManagerTest {
    private ProjectManager pm, pm2;
    private String firstName;
    private String lastName;
    private LocalDate date, secDate;
    private String description;
    private int assigneeId, pmId, taskId;
    private Help help, help2;
    //New not empty helpRequest lists
    private HashSet<Help> helpRequests_list = new HashSet<>();
    private HashSet<Help> helpRequests_list_short = new HashSet<>();
    //New empty helpRequest lists
    private HashSet<Help> helpRequests_list2 = new HashSet<>();
    private HashSet<Help> helpRequests_list2_short = new HashSet<>();
    //new UniqueIdGenerator
    UniqueIdGenerator<Assignee> uigAssignee = new UniqueIdGenerator<>();
    UniqueIdGenerator<Help> uigHelp = new UniqueIdGenerator<>();
    @Before
    public void setUp() throws StringTooShortException, ValueAlreadyExistsException {
        pmId = 5;
        firstName = "Jerycho";
        lastName = "Swain";

        date = LocalDate.now();
        secDate = LocalDate.now();
        description = "Just for testing purposes";
        assigneeId = 2;
        taskId = 2;
        help = new Help(uigHelp, date, description, assigneeId, pmId, taskId);
        help2 = new Help(uigHelp, secDate, "Smth", assigneeId, pmId, taskId);

        helpRequests_list.add(help);
        helpRequests_list.add(help2);
        helpRequests_list_short.add(help2);

        pm = new ProjectManager(uigAssignee, firstName, lastName);
        pm.setHelpRequests_list(helpRequests_list);
        pm2 = new ProjectManager(uigAssignee, "Dude", "Dudowski");
        pm2.setHelpRequests_list(helpRequests_list2);
    }

    /**
     * Get the given values belonging to ProjectManager object
     * ProjectManager object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetHelpRequests_list() {
        assertEquals(helpRequests_list, pm.getHelpRequests_list());
    }

    /**
     * Set the given values to fields belonging to ProjectManager object
     * ProjectManager object values are set anew and returned accordingly for each setter
     * Cases:  1) The helpRequests_list in Assignee is not empty and is set to a new empty list
     *         2) The helpRequests_list in Assignee is empty and is set to a new not empty list
     *         3) The helpRequests_list in Assignee is empty and is set to a new empty list
     *         4) The helpRequests_list in Assignee is not empty and is set to a new not empty list
     * Corner cases: setHelpRequests_list is tested on the matter of invalid input list (null)
     */

    @Test
    public void testSetHelpRequests_listFromNotEmptyToEmpty() {
        assertFalse(pm.getHelpRequests_list().isEmpty());
        pm.setHelpRequests_list(helpRequests_list2);
        assertEquals(helpRequests_list2, pm.getHelpRequests_list());
    }

    @Test
    public void testSetHelpRequests_listFromEmptyToNotEmpty() {
        assertTrue(pm2.getHelpRequests_list().isEmpty());
        pm2.setHelpRequests_list(helpRequests_list);
        assertEquals(helpRequests_list, pm2.getHelpRequests_list());
    }

    @Test
    public void testSetHelpRequests_listFromEmptyToEmpty() {
        assertTrue(pm2.getHelpRequests_list().isEmpty());
        pm2.setHelpRequests_list(helpRequests_list2_short);
        assertEquals(helpRequests_list2_short, pm2.getHelpRequests_list());
    }

    @Test
    public void testSetHelpRequests_listFromNotEmptyToNotEmpty() {
        assertFalse(pm.getHelpRequests_list().isEmpty());
        pm.setHelpRequests_list(helpRequests_list_short);
        assertEquals(helpRequests_list_short, pm.getHelpRequests_list());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetHelpRequests_listToNull() {
        pm.setHelpRequests_list(null);
    }
}