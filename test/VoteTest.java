import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class VoteTest {
    private int id1, id2;
    private String explanation1, explanation2;
    private int votedForId1, votedForId2;
    private int voterId1, voterId2;
    private Vote vote1;
    private Task task, task2;
    private Team team;
    private ProjectManager projectManager;

    @Before
    public void setUp() throws StringTooShortException {
        id1 = 5;
        id2 = 7;
        explanation1 = "Blah";
        explanation2 = "Blah2";
        votedForId1 = 3;
        votedForId2 = 5;
        voterId1 = 15;
        voterId2 = 23;

        projectManager = new ProjectManager(1,"Project", "Manager");
        team = new Team(1, "TestTeam", "This is a team for testing", projectManager, new HashSet<>());
        team.getAssignees().add(new Assignee(voterId1,"Tst","bloke"));
        team.getAssignees().add(new Assignee(voterId2,"Tst","gal"));
        team.getAssignees().add(new Assignee(votedForId1,"Tst1","bloke"));
        team.getAssignees().add(new Assignee(votedForId2,"Tst1","gal"));

        task = new Task(1, "TestTask", "It's a test, good good test", LocalDate.now(), null, LocalDate.now(), Status.ASSIGNED, team);
        task2 = new Task(2, "TestTask2", "It's a test2, good, good test2", LocalDate.now(), null, LocalDate.now(), Status.APPROVED, team);
        vote1 = new Vote(id1, task, explanation1, votedForId1, voterId1);
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
        new Vote(1, task, "BIIIIIIiiiiiigggggggg", 1, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException {
        new Vote(-1, null, null, -1, -1);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException {
        new Vote(1, task, "", 1, 1);
    }

    /**
     * Get the given values belonging to Vote object
     * Vote object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(id1, vote1.getId());
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
        assertEquals(id1, vote1.getId());
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
