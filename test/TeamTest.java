import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class TeamTest {
    private int id;
    private String name;
    private String description;
    private ProjectManager pm;
    private HashSet<Assignee> assignees = new HashSet<>();
    private HashSet<Task> tasks = new HashSet<>();
    private Team team;

    @Before
    public void setUp() throws StringTooShortException {
        id = 1;
        name = "Best Team";
        description = "Just for testing purposes";
        pm = new ProjectManager(1, "Jake", "Peralta");
        Assignee a1 = new Assignee(1, "Me", "Worker");
        Assignee a2 = new Assignee(2, "Me2", "Worker2");
        assignees.add(pm); assignees.add(a1); assignees.add(a2);
        team = new Team(id, name, description, pm, assignees);
    }

    /**
     * Initiate constructor with various values
     * Corner cases:
     * - values < 0 passed to the id field, null description/name/pm/assignees
     * - description.length < 15 / name.length < 3
     */

    @Test
    public void testConstructor() throws StringTooShortException {
        new Team(1, "Good", "Teeeeeeeaaaaammmmm", pm, assignees);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException {
        new Team(-1, null, null, null, null);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException {
        new Team(0, "", "", pm, assignees);
    }

    /**
     * Get the given values belonging to Team object
     * Team object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(id, team.getId());
    }

    @Test
    public void testGetName() {
        assertEquals(name, team.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals(description, team.getDescription());
    }

    @Test
    public void testGetPm() {
        assertEquals(pm, team.getPM());
    }

    @Test
    public void testGetAssignees() {
        assertEquals(assignees, team.getAssignees());
    }

    @Test
    public void testGetTasks() {
        assertEquals(tasks, team.getTasks());
    }

    /**
     * Set the {attribute} value of Vote object
     * Vote object {attribute} is set anew and returned accordingly for each setter
     * Corner cases:
     * - values < 0 passed to the id field, null description/name/pm/assignees
     * - description.length < 15 / name.length < 3
     */

    @Test
    public void testSetId() {
        assertEquals(id, team.getId());
        team.setId(15);
        assertEquals(15, team.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdIllegalArgument() {
        team.setId(-1);
    }

    @Test
    public void testSetName() throws StringTooShortException {
        assertEquals(name, team.getName());
        team.setName("FFF");
        assertEquals("FFF", team.getName());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetNameIllegalArgument() throws StringTooShortException {
        team.setName(null);
    }

    @Test(expected=StringTooShortException.class)
    public void testSetNameStringTooShort() throws StringTooShortException {
        team.setName("ai");
    }

    @Test
    public void testSetDescription() throws StringTooShortException {
        assertEquals(description, team.getDescription());
        team.setDescription("Very gooooooooooood");
        assertEquals("Very gooooooooooood", team.getDescription());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDescriptionIllegalArgument() throws StringTooShortException {
        team.setDescription(null);
    }

    @Test(expected=StringTooShortException.class)
    public void testSetDescriptionStringTooShort() throws StringTooShortException {
        team.setDescription("short");
    }

    @Test
    public void testSetPm() throws StringTooShortException {
        assertEquals(pm, team.getPM());
        ProjectManager newPM = new ProjectManager(777, "PM", "TheBest");
        team.setPM(newPM);
        assertEquals(newPM, team.getPM());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetPmIllegalArgument() {
        team.setPM(null);
    }

    //TODO add many different lists and test them accordingly (refer to Assignee tests for lists)

    @Test
    public void testSetAssignees() throws StringTooShortException {
        Assignee a3 = new Assignee(1, "Me3", "Worker3");
        Assignee a4 = new Assignee(2, "Me4", "Worker4");
        HashSet<Assignee> list = new HashSet<>();
        list.add(a3); list.add(a4);
        team.setAssignees(list);
        assertEquals(list, team.getAssignees());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetAssigneesToNull() {
        team.setAssignees(null);
    }

    @Test
    public void testSetTasks() throws StringTooShortException {
        //TODO fix -- sometimes gives "Start date cannot be in past" exception
        //the problem is in the order, in which tests are run
        //we compare startDate with current datetime (look in Task constructor), so if time passes in these little seconds,
        //startdate becomes invalid
        //solution - set startDate here not to current time, but to some time in the future
        //also, check out dates in other test files - maybe we need to do the same there

        Task t1 = new Task(1, "name", "jfjgfgffggfgffl", LocalDate.now(), null, LocalDate.now(), Status.APPROVED, team);
        Task t2 = new Task(2, "name2", "jfjfhfhfhhfhffnf", LocalDate.now(), null, LocalDate.now(), Status.APPROVED, team);
        HashSet<Task> list = new HashSet<>();
        list.add(t1); list.add(t2);
        team.setTasks(list);
        assertEquals(list, team.getTasks());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTasksToNull() {
        team.setTasks(null);
    }
}