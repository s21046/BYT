import ApplicationExceptions.StringTooShortException;
import ApplicationExceptions.ValueAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class VoteTest {
    private int id2;
    private String explanation1, explanation2;
    private int votedForId1, votedForId2;
    private int voterId1, voterId2;
    private Vote vote1;
    private Task task, task2;
    private Team team;
    private ProjectManager projectManager;

    UniqueIdGenerator<Assignee> assigneeUniqueIdGenerator= new UniqueIdGenerator<>();
    UniqueIdGenerator<Team> teamUniqueIdGenerator= new UniqueIdGenerator<>();
    UniqueIdGenerator<Task> taskUniqueIdGenerator= new UniqueIdGenerator<>();
    UniqueIdGenerator<Vote> voteUniqueIdGenerator= new UniqueIdGenerator<>();

    @Before
    public void setUp() throws StringTooShortException, ValueAlreadyExistsException {
        id2 = 7;
        explanation1 = "Blah";
        explanation2 = "Blah2";
        votedForId1 = 0;
        votedForId2 = 1;
        voterId1 = 2;
        voterId2 = 3;

        projectManager = new ProjectManager("Project", "Manager");
        projectManager.setId(assigneeUniqueIdGenerator.generateId(projectManager));
        team = new Team("TestTeam", "This is a team for testing", projectManager, new HashSet<>());
        team.setId(teamUniqueIdGenerator.generateId(team));
        Assignee a1 = new Assignee("Tst","bloke");
        a1.setId(assigneeUniqueIdGenerator.generateId(a1));
        Assignee a2 = new Assignee("Tst","gal");
        a2.setId(assigneeUniqueIdGenerator.generateId(a2));
        Assignee a3 = new Assignee("Tst1","bloke");
        a3.setId(assigneeUniqueIdGenerator.generateId(a3));
        Assignee a4 = new Assignee("Tst1","gal");
        a4.setId(assigneeUniqueIdGenerator.generateId(a4));
        team.getAssignees().add(a1);
        team.getAssignees().add(a2);
        team.getAssignees().add(a3);
        team.getAssignees().add(a4);

        task = new Task( "TestTask", "It's a test, good good test", LocalDate.now(), null, LocalDate.now(), Status.ASSIGNED, team);
        task.setId(taskUniqueIdGenerator.generateId(task));
        task2 = new Task( "TestTask2", "It's a test2, good, good test2", LocalDate.now(), null, LocalDate.now(), Status.APPROVED, team);
        task2.setId(taskUniqueIdGenerator.generateId(task2));
        vote1 = new Vote( task, explanation1, votedForId1, voterId1);
        vote1.setId(voteUniqueIdGenerator.generateId(vote1));
        task.getVotes_list().add(vote1);
    }

    /**
     * Initiate constructor with various values
     * Corner cases:
     * - values < 0 passed to the id field, null Task/explanation
     * - empty explanation
     */

    @Test
    public void testConstructor() throws StringTooShortException {
        new Vote(task, "BIIIIIIiiiiiigggggggg", 1, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException {
        new Vote(null, null, -1, -1);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException {
        new Vote(task, "", 1, 1);
    }

    /**
     * Get the given values belonging to Vote object
     * Vote object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(0, vote1.getId());
    }

    @Test
    public void testGetTask() {
        assertEquals(task, vote1.getTask());
    }

    @Test
    public void testGetExplanation() {
        assertEquals(explanation1, vote1.getExplanation());
    }

    @Test
    public void testGetVotedForId() {
        assertEquals(votedForId1, vote1.getVotedForId());
    }

    @Test
    public void testGetVoterId() {
        assertEquals(voterId1, vote1.getVoterId());
    }

    /**
     * Set the {attribute} value of Vote object
     * Vote object {attribute} is set anew and returned accordingly for each setter
     * Corner cases:
     * - values < 0 passed to the id field, null Task/explanation
     * - empty explanation
     */

    @Test
    public void testSetId() {
        assertEquals(0, vote1.getId());
        vote1.setId(id2);
        assertEquals(id2, vote1.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdIllegalArgument() {
        vote1.setId(-1);
    }

    @Test
    public void testSetTask() {
        assertEquals(task, vote1.getTask());
        vote1.setTask(task2);
        assertEquals(task2, vote1.getTask());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTaskIllegalArgument() {
        vote1.setTask(null);
    }

    @Test
    public void testSetExplanation() throws StringTooShortException {
        assertEquals(explanation1, vote1.getExplanation());
        vote1.setExplanation(explanation2);
        assertEquals(explanation2, vote1.getExplanation());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetExplanationIllegalArgument() throws StringTooShortException {
        vote1.setExplanation(null);
    }

    @Test(expected=StringTooShortException.class)
    public void testSetExplanationStringTooShort() throws StringTooShortException {
        vote1.setExplanation("");
    }

    @Test
    public void testSetVotedForId() {
        assertEquals(votedForId1, vote1.getVotedForId());
        vote1.setVotedForId(votedForId2);
        assertEquals(votedForId2, vote1.getVotedForId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetVotedForIdIllegalArgumentException() {
        vote1.setVotedForId(-1);
    }

    @Test
    public void testSetVoterId() {
        assertEquals(voterId1, vote1.getVoterId());
        vote1.setVoterId(voterId2);
        assertEquals(voterId2,vote1.getVoterId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetVoterIdIllegalArgumentException() {
        vote1.setVoterId(-1);
    }
}
