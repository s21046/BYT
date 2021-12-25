import ApplicationExceptions.StringTooShortException;
import ApplicationExceptions.ValueAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class AssigneeTest {
    private Assignee assignee, assignee1;
    private String firstName, firstName1;
    private String lastName, lastName1;
    private ProjectManager pm;
    private LocalDate day1, day2, day3;
    private Task task1, task2;
    private Reward rew1, rew2;
    private RewardType rtype1, rtype2;
    private Status stat1, stat2;
    private Suggestion sug1, sug2;
    private Team team1, team2;
    //Two not empty tasks lists
    private HashSet<Task> tasks_list = new HashSet<>();
    private HashSet<Task> tasks_list_short = new HashSet<>();
    //Two empty tasks lists
    private HashSet<Task> tasks_list2 = new HashSet<>();
    private HashSet<Task> tasks_list_2_short = new HashSet<>();
    //Two not empty rewards lists
    private List<Reward> rewards_list = new ArrayList<>();
    private List<Reward> rewards_list_short = new ArrayList<>();
    //Two empty rewards lists
    private List<Reward> rewards_list2_short = new ArrayList<>();
    private List<Reward> rewards_list2 = new ArrayList<>();
    //Two not empty suggestions lists
    private HashSet<Suggestion> suggestions_list = new HashSet<>();
    private HashSet<Suggestion> suggestions_list_short = new HashSet<>();
    //Two empty suggestions lists
    private HashSet<Suggestion> suggestions_list2 = new HashSet<>();
    private HashSet<Suggestion> suggestions_list2_short = new HashSet<>();
    //Two not empty teams lists
    private HashSet<Team> teams_list = new HashSet<>();
    private HashSet<Team> teams_list_short = new HashSet<>();
    //Two empty teams lists
    private HashSet<Team> teams_list2 = new HashSet<>();
    private HashSet<Team> teams_list2_short = new HashSet<>();

    //UnqiueIdGenerators
    UniqueIdGenerator<Assignee> assigneeUniqueIdGenerator;
    UniqueIdGenerator<Team> teamUniqueIdGenerator;
    UniqueIdGenerator<Reward> rewardUniqueIdGenerator;
    UniqueIdGenerator<Task> taskUniqueIdGenerator;
    UniqueIdGenerator<Suggestion> suggestionUniqueIdGenerator;


    @Before
    public void setUp() throws StringTooShortException, ValueAlreadyExistsException {
        assigneeUniqueIdGenerator = new UniqueIdGenerator<>();
        teamUniqueIdGenerator = new UniqueIdGenerator<>();
        rewardUniqueIdGenerator = new UniqueIdGenerator<>();
        taskUniqueIdGenerator = new UniqueIdGenerator<>();
        suggestionUniqueIdGenerator = new UniqueIdGenerator<>();
        firstName = "John";
        firstName1 = "Adam";
        lastName = "Cena";
        lastName1 = "Stanowski";

        stat1 = Status.APPROVED;
        stat2 = Status.ASSIGNED;

        rtype1 = RewardType.BADGE;
        rtype2 = RewardType.TITLE;

        day1 = LocalDate.now();
        day2 = LocalDate.now();
        day3 = LocalDate.now();

        pm = new ProjectManager("Jerycho", "Swain");
        pm.setId(assigneeUniqueIdGenerator.generateId(pm));

        team1 = new Team("Birbs", "Focus on testing", pm, new HashSet<>());
        team1.setId(teamUniqueIdGenerator.generateId(team1));
        team2 = new Team("Cats", "Write code here pls", pm, new HashSet<>());
        team2.setId(teamUniqueIdGenerator.generateId(team2));

        teams_list.add(team1);
        teams_list.add(team2);
        teams_list_short.add(team1);

        task1 = new Task("Run", "Just runninnnnnnnnn", day1, null, day2, stat1, team1);
        task1.setId(taskUniqueIdGenerator.generateId(task1));
        task2 = new Task("Stop", "Just stoppinnnnnnnnn", day2, null, day3, stat2, team2);
        task2.setId(taskUniqueIdGenerator.generateId(task2));
        tasks_list.add(task1);
        tasks_list.add(task2);
        tasks_list_short.add(task1);

        rew1 = new Reward("Golden Duck", "Quackkkkkkkkkkkkkkkkk", rtype1,day2);
        rew1.setId(rewardUniqueIdGenerator.generateId(rew1));
        rew2 = new Reward( "Rainbow", "Colorfullllllllllllll", rtype2, day3);
        rew2.setId(rewardUniqueIdGenerator.generateId(rew2));
        rewards_list.add(rew1);
        rewards_list.add(rew2);
        rewards_list_short.add(rew1);

        sug1 = new Suggestion("Code cleanup", "Delete unused imports", 2);
        sug1.setId(suggestionUniqueIdGenerator.generateId(sug1));
        sug2 = new Suggestion( "Add more tests", "We need to test our code", 5);
        sug2.setId(suggestionUniqueIdGenerator.generateId(sug2));
        suggestions_list.add(sug1);
        suggestions_list.add(sug2);
        suggestions_list_short.add(sug1);

        assignee = new Assignee( firstName, lastName);
        assignee.setId(assigneeUniqueIdGenerator.generateId(assignee));
        assignee.setTasks_list(tasks_list);
        assignee.setSuggestions_list(suggestions_list);
        assignee.setRewards_list(rewards_list);
        assignee.setTeams_list(teams_list);

        assignee1 = new Assignee( firstName1,lastName1);
        assignee1.setId(assigneeUniqueIdGenerator.generateId(assignee1));
        assignee1.setTasks_list(tasks_list2);
        assignee1.setSuggestions_list(suggestions_list2);
        assignee1.setRewards_list(rewards_list2);
        assignee1.setTeams_list(teams_list2);
    }

    /**
     * Initiate constructor with various values
     * Corner cases:
     * - values < 0 passed to the id field, null firstName/lastName;
     * - empty firstName/lastName
     */

    @Test
    public void testConstructor() throws StringTooShortException {
        new Assignee("Me", "Assignee");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException {
        new Assignee(null, null);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException {
        new Assignee("", "");
    }

    /**
     * Get the given values belonging to Assignee object
     * Assignee object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(1, assignee.getId());
        assertEquals(0, pm.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals(firstName, assignee.getFirstName());
        assertEquals("Jerycho", pm.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals(lastName, assignee.getLastName());
        assertEquals("Swain", pm.getLastName());
    }

    @Test
    public void testGetTasks_list() {
        assertEquals(tasks_list, assignee.getTasks_list());
    }

    @Test
    public void testGetRewards_list() {
        assertEquals(rewards_list, assignee.getRewards_list());
    }

    @Test
    public void testGetTeams_list() {
        assertEquals(teams_list, assignee.getTeams_list());
    }

    @Test
    public void testGetSuggestions_list() {
        assertEquals(suggestions_list, assignee.getSuggestions_list());
    }

    /**
     * Set the {attribute} value of Assignee object
     * Assignee object {attribute} is set anew and returned accordingly for each setter
     * Corner cases:
     * - values < 0 passed to the id field, null firstName/lastName;
     * - empty firstName/lastName
     */

    @Test
    public void testSetId() {
        assertEquals(1, assignee.getId());
        assignee.setId(14);
        assertEquals(14, assignee.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdIllegalArgument() {
        assignee.setId(-1);
    }

    @Test
    public void testSetFirstName() throws StringTooShortException {
        assertEquals("John", assignee.getFirstName());
        assignee.setFirstName("Bobinator");
        assertEquals("Bobinator", assignee.getFirstName());
    }

    @Test(expected=StringTooShortException.class)
    public void testSetFirstNameStringTooShort() throws StringTooShortException {
        assignee.setFirstName("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetFirstNameIllegalArgument() throws StringTooShortException {
        assignee.setFirstName(null);
    }

    @Test
    public void testSetLastName() throws StringTooShortException {
        assertEquals("Cena", assignee.getLastName());
        assignee.setLastName("Jason");
        assertEquals("Jason", assignee.getLastName());
    }

    @Test(expected=StringTooShortException.class)
    public void testSetLastNameStringTooShort() throws StringTooShortException {
        assignee.setLastName("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetLastNameIllegalArgument() throws StringTooShortException {
        assignee.setLastName(null);
    }

    /**
     * Set the {objects}_list value of Assignee object
     * Assignee object {objects}_list is set anew and returned accordingly for each setter
     * Cases:  1) The {objects}_list in Assignee is not empty and is set to a new empty list
     *         2) The {objects}_list in Assignee is empty and is set to a new not empty list
     *         3) The {objects}_list in Assignee is empty and is set to a new empty list
     *         4) The {objects}_list in Assignee is not empty and is set to a new not empty list
     * Corner cases: input list is null
     */

    @Test
    public void testSetTasks_listFromNotEmptyToEmpty() {
        assertFalse(assignee.getTasks_list().isEmpty());
        assignee.setTasks_list(tasks_list2);
        assertEquals(tasks_list2, assignee.getTasks_list());
    }

    @Test
    public void testSetTasks_listFromEmptyToNotEmpty() {
        assertTrue(assignee1.getTasks_list().isEmpty());
        assignee1.setTasks_list(tasks_list);
        assertEquals(tasks_list, assignee1.getTasks_list());
    }

    @Test
    public void testSetTasks_listFromEmptyToEmpty() {
        assertTrue(assignee1.getTasks_list().isEmpty());
        assignee1.setTasks_list(tasks_list_2_short);
        assertEquals(tasks_list_2_short, assignee1.getTasks_list());
    }

    @Test
    public void testSetTasks_listFromNotEmptyToNotEmpty() {
        assertFalse(assignee.getTasks_list().isEmpty());
        assignee.setTasks_list(tasks_list_short);
        assertEquals(tasks_list_short, assignee.getTasks_list());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTasks_listToNullException() {
        assignee.setTasks_list(null);
    }

    @Test
    public void testSetRewards_listFromNotEmptyToEmpty() {
        assertFalse(assignee.getRewards_list().isEmpty());
        assignee.setRewards_list(rewards_list2);
        assertEquals(rewards_list2, assignee.getRewards_list());
    }

    @Test
    public void testSetRewards_listFromEmptyToNotEmpty() {
        assertTrue(assignee1.getRewards_list().isEmpty());
        assignee1.setRewards_list(rewards_list);
        assertEquals(rewards_list, assignee1.getRewards_list());
    }

    @Test
    public void testSetRewards_listFromEmptyToEmpty() {
        assertTrue(assignee1.getRewards_list().isEmpty());
        assignee1.setRewards_list(rewards_list2_short);
        assertEquals(rewards_list2_short, assignee1.getRewards_list());
    }

    @Test
    public void testSetRewards_listFromNotEmptyToNotEmpty() {
        assertFalse(assignee.getRewards_list().isEmpty());
        assignee.setRewards_list(rewards_list_short);
        assertEquals(rewards_list_short, assignee.getRewards_list());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetRewards_listToNullException() {
        assignee.setRewards_list(null);
    }

    @Test
    public void testSetSuggestions_listFromNotEmptyToEmpty() {
        assertFalse(assignee.getSuggestions_list().isEmpty());
        assignee.setSuggestions_list(suggestions_list2);
        assertEquals(suggestions_list2, assignee.getSuggestions_list());
    }

    @Test
    public void testSetSuggestions_listFromEmptyToNotEmpty() {
        assertTrue(assignee1.getSuggestions_list().isEmpty());
        assignee1.setSuggestions_list(suggestions_list);
        assertEquals(suggestions_list, assignee1.getSuggestions_list());
    }

    @Test
    public void testSetSuggestions_listFromEmptyToEmpty() {
        assertTrue(assignee1.getSuggestions_list().isEmpty());
        assignee1.setSuggestions_list(suggestions_list2_short);
        assertEquals(suggestions_list2_short, assignee1.getSuggestions_list());
    }

    @Test
    public void testSetSuggestions_listFromNotEmptyToNotEmpty() {
        assertFalse(assignee.getSuggestions_list().isEmpty());
        assignee.setSuggestions_list(suggestions_list_short);
        assertEquals(suggestions_list_short, assignee.getSuggestions_list());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetSuggestions_listToNullException() {
        assignee.setSuggestions_list(null);
    }

    @Test
    public void testSetTeams_listFromNotEmptyToEmpty() {
        assertFalse(assignee.getTeams_list().isEmpty());
        assignee.setTeams_list(teams_list2);
        assertEquals(teams_list2, assignee.getTeams_list());
    }

    @Test
    public void testSetTeams_listFromEmptyToNotEmpty() {
        assertTrue(assignee1.getTeams_list().isEmpty());
        assignee1.setTeams_list(teams_list);
        assertEquals(teams_list, assignee1.getTeams_list());
    }

    @Test
    public void testSetTeams_listFromEmptyToEmpty() {
        assertTrue(assignee1.getTeams_list().isEmpty());
        assignee1.setTeams_list(teams_list2_short);
        assertEquals(teams_list2_short, assignee1.getTeams_list());
    }

    @Test
    public void testSetTeams_listFromNotEmptyToNotEmpty() {
        assertFalse(assignee.getTeams_list().isEmpty());
        assignee.setTeams_list(teams_list_short);
        assertEquals(teams_list_short, assignee.getTeams_list());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTeams_listToNull() {
        assignee.setTeams_list(null);
    }
}