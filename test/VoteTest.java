import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VoteTest {


    private int id1;
    private int id2;
    private String explanation1;
    private String explanation2;
    private int votedForId1;
    private int votedForId2;
    private int voterId1;
    private int voterId2;
    private Vote vote1;
    private Task task;
    private Task task2;
    private Team team;
    private ProjectManager projectManager;

    @Before
    public void setUp(){
        id1 = 5;
        id2 = 7;
        explanation1 = "Blah";
        explanation2 = "Blah2";
        votedForId1 = 3;
        votedForId2 = 5;
        voterId1 = 15;
        voterId2 = 23;

        projectManager = new ProjectManager(1,"Project", "Manager");
        team = new Team(1, "TestTeam", "This is a team for testing", projectManager, new ArrayList<Assignee>());
        team.getAssignees().add(new Assignee(voterId1,"Tst","bloke"));
        team.getAssignees().add(new Assignee(voterId2,"Tst","gal"));
        team.getAssignees().add(new Assignee(votedForId1,"Tst1","bloke"));
        team.getAssignees().add(new Assignee(votedForId2,"Tst1","gal"));

        task = new Task(1,"TestTask","It's a test", new Date(2021,12,19),new Date(2021,12,21),Status.ASSIGNED,team);
        task2 = new Task(2,"TestTask2","It's a test2", new Date(2021,12,18),new Date(2021,12,24),Status.APPROVED,team);
        vote1 = new Vote(id1,explanation1,votedForId1,voterId1,task);
        task.getVotes_list().add(vote1);

    }

    /**
     * Get the given values belonging to Vote object
     * @result Help object values are returned accordingly for each getter
     * @corner_cases None
     */

    @Test
    public void testGetId() {
        assertEquals(id1,vote1.getId());
    }

    @Test
    public void testGetExplanation() {
        assertEquals(explanation1,vote1.getExplanation());
    }

    @Test
    public void testGetVotedForId() {
        assertEquals(votedForId1,vote1.getVotedForId());
    }

    @Test
    public void testGetVoterId() {
        assertEquals(voterId1,vote1.getVoterId());
    }

    @Test
    public void testGetTask() {
        assertEquals(task,vote1.getTask());
    }

    /**
     * Set the id value to Vote object
     * @result Vote object id is set anew and returned accordingly for each setter
     * @corner_case setId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void testSetId() {
        vote1.setId(id2);
        assertEquals(id2,vote1.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdIllegalArgumentException() {
        vote1.setId(-1);
    }

    /**
     * Set the Explanation value to Vote object
     * @result Vote object Explanation is set anew and returned accordingly for each setter
     * @corner_cases setExplanation is tested on the matter of invalid input:
     *                         1) Empty String
     *                         2) Null
     */

    @Test
    public void testSetExplanation() throws StringTooShortException {
        assertEquals(explanation1, vote1.getExplanation());
        vote1.setExplanation(explanation2);
        assertEquals(explanation2,vote1.getExplanation());
    }

    @Test(expected=StringTooShortException.class)
    public void testSetExplanationStringTooShortException() throws StringTooShortException {
        vote1.setExplanation("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetExplanationIllegalArgumentException() throws StringTooShortException {
        vote1.setExplanation(null);
    }

    /**
     * Set the votedForId value to Vote object
     * @result Vote object votedForId is set anew and returned accordingly for each setter
     * @corner_case setVotedForId is tested on the matter of invalid input id (Not maching id)
     */

    @Test
    public void testSetVotedForId() {
        vote1.setVotedForId(votedForId2);
        assertEquals(votedForId2,vote1.getVotedForId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetVotedForIdIllegalArgumentException() {
        vote1.setVotedForId(-1);
    }

    /**
     * Set the voterId value to Vote object
     * @result Vote object voterId is set anew and returned accordingly for each setter
     * @corner_case setVoterId is tested on the matter of invalid input id (Not maching id)
     */

    @Test
    public void testSetVoterId() {
        vote1.setVoterId(voterId2);
        assertEquals(voterId2,vote1.getVoterId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetVoterIdIllegalArgumentException() {
        vote1.setVoterId(-1);
    }

    /**
     * Set the task value to Vote object
     * @result Vote object task is set anew and returned accordingly for each setter
     * @corner_case setTask is tested on the matter of invalid input task (null argument)
     */

    @Test
    public void testSetTask() {
        vote1.setTask(task2);
        assertEquals(task2,vote1.getTask());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTaskIllegalArgumentException() {
        vote1.setTask(null);
    }
}
