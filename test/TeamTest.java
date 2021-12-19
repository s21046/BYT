import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TeamTest {
    private int id;
    private String name;
    private String description;
    private ProjectManager pm;
    private List<Assignee> assignees = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private Team team;

    //Corner cases to be added
    @Before
    public void setUp() {
        id = 1;
        name = "Best Team";
        description = "Just for testing purposes";
        pm = new ProjectManager(1, "Jake", "Peralta");
        Assignee a1 = new Assignee(1, "Me", "Worker");
        Assignee a2 = new Assignee(2, "Me2", "Worker2");
        assignees.add(a1); assignees.add(a2);
        team = new Team(id, name, description, pm, assignees);
    }

    /**
     * Get the given values belonging to Team object
     * @result Team object values are returned accordingly for each getter
     * @corner_cases None
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
     * Set the given values to fields belonging to Team object
     * @result Team object values are set anew and returned accordingly for each setter
     * @corner_cases setId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void testSetId() {
        team.setId(15);
        assertEquals(15, team.getId());
    }

    @Test
    public void testSetName() throws StringTooShortException {
        team.setName("FFF");
        assertEquals("FFF", team.getName());
    }

    @Test
    public void testSetDescription() throws StringTooShortException {
        team.setDescription("Very gooooooooooood");
        assertEquals("Very gooooooooooood", team.getDescription());
    }

    @Test
    public void testSetPm() {
        ProjectManager pm = new ProjectManager(777, "PM", "TheBest");
        team.setPM(pm);
        assertEquals(pm.getId(), team.getPM().getId());
    }

    @Test
    public void testSetAssignees() {
        Assignee a3 = new Assignee(1, "Me3", "Worker3");
        Assignee a4 = new Assignee(2, "Me4", "Worker4");
        List<Assignee> list = new ArrayList<>();
        list.add(a3); list.add(a4);
        team.setAssignees(list);
        assertEquals(list.size(), team.getAssignees().size());
        assertEquals(list.get(0).getId(), team.getAssignees().get(0).getId());
        assertEquals(list.get(1).getId(), team.getAssignees().get(1).getId());
    }

    @Test
    public void testSetTasks() {
        Task t1 = new Task(1, "name", "jfj", new Date(), new Date(), Status.APPROVED, team);
        Task t2 = new Task(2, "name2", "jfj", new Date(), new Date(), Status.APPROVED, team);
        List<Task> list = new ArrayList<>();
        list.add(t1); list.add(t2);
        team.setTasks(list);
        assertEquals(list.size(), team.getTasks().size());
        assertEquals(list.get(0).getId(), team.getTasks().get(0).getId());
        assertEquals(list.get(1).getId(), team.getTasks().get(1).getId());
    }
}