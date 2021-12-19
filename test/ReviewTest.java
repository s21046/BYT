import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ReviewTest {
    private int id;
    private String description;
    private int assigneeId;
    private int taskId;
    private boolean approved;
    private Task task;
    private Date day1;
    private Date day2;
    private Status status;
    private Team team;
    private ProjectManager pm;
    private Review rev;

    public void setUp(){
        id = 5;
        description = "Quack";
        assigneeId = 1;
        taskId = 2;
        approved = true;
        status = Status.ASSIGNED;
        pm = new ProjectManager(2,"Jerycho", "Swain");
        team = new Team(1,"Birbs", "Focus on testing", pm, null);
        day1 = new Date();
        day2 = new Date();
        task = new Task(1, "Some task", "Nothing", day1, day2, status, team);
        rev = new Review(id, approved, description, assigneeId, taskId);
    }

    /**
     * Get the given values belonging to Review object
     * @result Review object values are returned accordingly for each getter
     * @corner_cases None
     */


    @Test
    public void testGetId() {
        assertEquals(id, rev.getId());
    }

    @Test
    public void getDescription() {
        assertEquals(description, rev.getDescription());
    }

    @Test
    public void getAssigneeId() {
        assertEquals(assigneeId, rev.getAssigneeId());
    }

    @Test
    public void getTaskId() {
        assertEquals(taskId, rev.getTaskId());
    }

    @Test
    public void isApproved() {
        assertEquals(approved, rev.isApproved());
    }


    @Test
    public void setId() {
    }

    @Test
    public void setDescription() {
    }

    @Test
    public void setAssigneeId() {
    }

    @Test
    public void setTaskId() {
    }

    @Test
    public void setApproved() {
    }
}