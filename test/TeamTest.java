import ApplicationExceptions.StringTooShortException;
import ApplicationExceptions.ValueAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TeamTest {
    private int id;
    private String name;
    private String description;
    private ProjectManager pm;
    //Two not empty assignees lists
    private HashSet<Assignee> assignees_list = new HashSet<>();
    private HashSet<Assignee> assignees_list_short = new HashSet<>();
    //Two empty assignees lists
    private HashSet<Assignee> assignees_list2 = new HashSet<>();
    private HashSet<Assignee> assignees_list_2_short = new HashSet<>();
    //Two not empty tasks lists
    private HashSet<Task> tasks_list = new HashSet<>();
    private HashSet<Task> tasks_list_short = new HashSet<>();
    //Two empty tasks lists
    private HashSet<Task> tasks_list2 = new HashSet<>();
    private HashSet<Task> tasks_list_2_short = new HashSet<>();
    private Team team, team2;

    UniqueIdGenerator<Assignee> assigneeUniqueIdGenerator= new UniqueIdGenerator<>();
    UniqueIdGenerator<Team> teamUniqueIdGenerator= new UniqueIdGenerator<>();
    UniqueIdGenerator<Reward> rewardUniqueIdGenerator= new UniqueIdGenerator<>();
    UniqueIdGenerator<Task> taskUniqueIdGenerator= new UniqueIdGenerator<>();
    UniqueIdGenerator<Suggestion> suggestionUniqueIdGenerator= new UniqueIdGenerator<>();
    UniqueIdGenerator<Review> reviewUniqueIdGenerator= new UniqueIdGenerator<>();
    UniqueIdGenerator<Vote> voteUniqueIdGenerator= new UniqueIdGenerator<>();

    @Before
    public void setUp() throws StringTooShortException, ValueAlreadyExistsException {
        id = 1;
        name = "Best Team";
        description = "Just for testing purposes";
        pm = new ProjectManager( "Jake", "Peralta");
        pm.setId(assigneeUniqueIdGenerator.generateId(pm));
        Assignee a1 = new Assignee( "Me", "Worker");
        a1.setId(assigneeUniqueIdGenerator.generateId(a1));
        Assignee a2 = new Assignee( "Me2", "Worker2");
        a2.setId(assigneeUniqueIdGenerator.generateId(a2));
        assignees_list.add(pm); assignees_list.add(a1); assignees_list.add(a2);
        assignees_list_short.add(pm); assignees_list_short.add(a1);
        team = new Team( name, description, pm, assignees_list);
        team.setId(teamUniqueIdGenerator.generateId(team));
        team2 = new Team( "good", "ggggggggggggggggggg", pm, assignees_list2);
        team2.setId(teamUniqueIdGenerator.generateId(team2));
        Task t1 = new Task( "name", "jfjgfgffggfgffl", LocalDate.now(), null, LocalDate.now(), Status.APPROVED, team);
        t1.setId(taskUniqueIdGenerator.generateId(t1));
        Task t2 = new Task( "name2", "jfjfhfhfhhfhffnf", LocalDate.now(), null, LocalDate.now(), Status.APPROVED, team);
        t2.setId(taskUniqueIdGenerator.generateId(t2));
        tasks_list.add(t1); tasks_list.add(t2);
        tasks_list_short.add(t1);
        team.setTasks(tasks_list); team2.setTasks(tasks_list2);
    }

    /**
     * Initiate constructor with various values
     * Corner cases:
     * - values < 0 passed to the id field, null description/name/pm/assignees
     * - description.length < 15 / name.length < 3
     */

    @Test
    public void testConstructor() throws StringTooShortException {
        new Team("Good", "Teeeeeeeaaaaammmmm", pm, assignees_list);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException {
        new Team(null, null, null, null);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException {
        new Team( "", "", pm, assignees_list);
    }

    /**
     * Get the given values belonging to Team object
     * Team object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(0, team.getId());
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
        assertEquals(assignees_list, team.getAssignees());
    }

    @Test
    public void testGetTasks() {
        assertEquals(tasks_list, team.getTasks());
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
        assertEquals(0, team.getId());
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
        ProjectManager newPM = new ProjectManager( "PM", "TheBest");
        team.setPM(newPM);
        assertEquals(newPM, team.getPM());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetPmIllegalArgument() {
        team.setPM(null);
    }

    /**
     * Set the {objects}_list value of Team object
     * Team object {objects}_list is set anew and returned accordingly for each setter
     * Cases:  1) The {objects}_list in Team is not empty and is set to a new empty list
     *         2) The {objects}_list in Team is empty and is set to a new not empty list
     *         3) The {objects}_list in Team is empty and is set to a new empty list
     *         4) The {objects}_list in Team is not empty and is set to a new not empty list
     * Corner cases: input list is null
     */

    @Test
    public void testSetAssignees() throws StringTooShortException {
        Assignee a3 = new Assignee("Me3", "Worker3");
        Assignee a4 = new Assignee("Me4", "Worker4");
        HashSet<Assignee> list = new HashSet<>();
        list.add(a3); list.add(a4);
        team.setAssignees(list);
        assertEquals(list, team.getAssignees());
    }

    @Test
    public void testSetAssignees_listFromNotEmptyToEmpty() {
        assertFalse(team.getAssignees().isEmpty());
        team.setAssignees(assignees_list2);
        assertEquals(assignees_list2, team.getAssignees());
    }

    @Test
    public void testSetAssignees_listFromEmptyToNotEmpty() {
        assertTrue(team2.getAssignees().isEmpty());
        team2.setAssignees(assignees_list);
        assertEquals(assignees_list, team2.getAssignees());
    }

    @Test
    public void testSetAssignees_listFromEmptyToEmpty() {
        assertTrue(team2.getAssignees().isEmpty());
        team2.setAssignees(assignees_list_2_short);
        assertEquals(assignees_list_2_short,team2.getAssignees());
    }

    @Test
    public void testSetAssignees_listFromNotEmptyToNotEmpty() {
        assertFalse(team.getAssignees().isEmpty());
        team.setAssignees(assignees_list_short);
        assertEquals(assignees_list_short, team.getAssignees());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetAssigneesToNull() {
        team.setAssignees(null);
    }

    @Test
    public void testSetTasks() throws StringTooShortException {
        Task t1 = new Task( "name", "jfjgfgffggfgffl", LocalDate.now(), null, LocalDate.now(), Status.APPROVED, team);
        Task t2 = new Task( "name2", "jfjfhfhfhhfhffnf", LocalDate.now(), null, LocalDate.now(), Status.APPROVED, team);
        HashSet<Task> list = new HashSet<>();
        list.add(t1); list.add(t2);
        team.setTasks(list);
        assertEquals(list, team.getTasks());
    }

    @Test
    public void testSetTasks_listFromNotEmptyToEmpty() {
        assertFalse(team.getTasks().isEmpty());
        team.setTasks(tasks_list2);
        assertEquals(tasks_list2, team.getTasks());
    }

    @Test
    public void testSetTasks_listFromEmptyToNotEmpty() {
        assertTrue(team2.getTasks().isEmpty());
        team2.setTasks(tasks_list);
        assertEquals(tasks_list, team2.getTasks());
    }

    @Test
    public void testSetTasks_listFromEmptyToEmpty() {
        assertTrue(team2.getTasks().isEmpty());
        team2.setTasks(tasks_list_2_short);
        assertEquals(tasks_list_2_short, team2.getTasks());
    }

    @Test
    public void testSetTasks_listFromNotEmptyToNotEmpty() {
        assertFalse(team.getTasks().isEmpty());
        team.setTasks(tasks_list_short);
        assertEquals(tasks_list_short, team.getTasks());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTasksToNull() {
        team.setTasks(null);
    }
}