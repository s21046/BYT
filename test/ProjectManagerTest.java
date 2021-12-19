import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * This test class covers only methods unique for ProjectManager.
 * For tests for all methods it inherits from Assignee refer to AssigneeTest.java
 */

public class ProjectManagerTest {
    private ProjectManager pm;
    private ProjectManager pm2;
    private String firstName;
    private String lastName;
    private Date date;
    private Date secDate;
    private String description;
    private int assigneeId, pmId, taskId;
    private Help help;
    private Help help2;
    //New not empty helpRequest lists
    private HashSet<Help> helpRequests_list = new HashSet<>();
    private HashSet<Help> helpRequests_list_short = new HashSet<>();
    //New empty helpRequest lists
    private HashSet<Help> helpRequests_list2 = new HashSet<>();
    private HashSet<Help> helpRequests_list2_short = new HashSet<>();

    /**
     * All values are randomized for the sake of testing
     */

    @Before
    public void setUp(){
        pmId = 5;
        firstName = "Jerycho";
        lastName = "Swain";

        date = new Date();
        secDate = new Date();
        description = "Just for testing purposes";
        assigneeId = 2;
        taskId = 2;
        help = new Help(1, date, description, assigneeId, pmId, taskId);
        help2 = new Help(2, secDate, "Smth", assigneeId, pmId, taskId);

        helpRequests_list.add(help);
        helpRequests_list.add(help2);
        helpRequests_list_short.add(help2);

        pm = new ProjectManager(pmId, firstName, lastName);
        pm.setHelpRequests_list(helpRequests_list);
        pm2 = new ProjectManager(66, "Dude", "Dudowski");
        pm2.setHelpRequests_list(helpRequests_list2);
    }

    /**
     * Get the given values belonging to ProjectManager object
     * @result ProjectManager object values are returned accordingly for each getter
     * @corner_cases None
     */

    @Test
    public void testGetHelpRequests_list() {
        assertEquals(helpRequests_list, pm.getHelpRequests_list());
    }

    /**
     * Set the given values to fields belonging to ProjectManager object
     * @result ProjectManager object values are set anew and returned accordingly for each setter
     * @cases  1) The helpRequests_list in Assignee is not empty and is set to a new empty list
     *         2) The helpRequests_list in Assignee is empty and is set to a new not empty list
     *         3) The helpRequests_list in Assignee is empty and is set to a new empty list
     *         4) The helpRequests_list in Assignee is not empty and is set to a new not empty list
     * @corner_case setHelpRequests_list is tested on the matter of invalid input list (null)
     */

    @Test
    public void testSetHelpRequests_listFromNotEmptyToEmpty() {
        pm.setHelpRequests_list(helpRequests_list2);
        assertEquals(helpRequests_list2, pm.getHelpRequests_list());
    }

    @Test
    public void testSetHelpRequests_listFromEmptyToNotEmpty() {
        pm2.setHelpRequests_list(helpRequests_list);
        assertEquals(helpRequests_list, pm2.getHelpRequests_list());
    }

    @Test
    public void testSetHelpRequests_listFromEmptyToEmpty() {
        pm2.setHelpRequests_list(helpRequests_list2_short);
        assertEquals(helpRequests_list2_short, pm2.getHelpRequests_list());
    }
    @Test
    public void testSetHelpRequests_listFromNotEmptyToNotEmpty() {
        pm.setHelpRequests_list(helpRequests_list_short);
        assertEquals(helpRequests_list_short, pm.getHelpRequests_list());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetHelpRequests_listToNull() {
        pm.setHelpRequests_list(null);
    }

}