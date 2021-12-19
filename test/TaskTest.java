import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TaskTest {
    int id1, id2, id3;
    String name1, name2, name3;
    String desc1, desc2, desc3;
    Date startDate1, startDate2, startDate3;
    Date deadline1, deadline2, deadline3;
    Status status;
    Team programmers;
    List<Assignee> assignees;
    ProjectManager pm;
	Task createUI;
	Task vacuumRoom;
	Task getSleep;
	
	@Before
	public void setUp() {
		pm = new ProjectManager(1, "Hana", "Busa");
		assignees = new ArrayList<>();
		assignees.add(pm);
		assignees.add(new Assignee(2, "Kuka", "Racza"));
		assignees.add(new Assignee(3, "Dalai", "Lama"));
		programmers = new Team(1, "Le Programmers", "Greatest programmers", pm, assignees);
		id1 = 1;
		id2 = 2;
		id3 = 3;
		name1 = "Create UI";
		name2 = "Vacuum your room";
		name3 = "Get some sleep";
		desc1 = "We need to create a nice UI for our application";
		desc2 = "Be a contributing member of society";
		desc3 = "do somthin";
		startDate1 = new Date();
		startDate2 = new Date();
		startDate3 = new Date();
		deadline1 = new Date();
		deadline2 = new Date();
		deadline3 = new Date();
		status = Status.APPROVED;
		createUI = new Task(id1, name1, desc1, startDate1, deadline1, status, programmers);
		vacuumRoom = new Task(id2, name2, desc2, startDate2, deadline2, status, programmers);
		getSleep = new Task(id3, name3, desc3, startDate3, deadline3, status, programmers);
	}
	
	  /**
     * Get the given values belonging to Task object
     * @result Task object values are returned accordingly for each getter
     * @corner_cases None
     */
	
	@Test
	public void testGetId() {
		assertEquals(createUI.getId(), id1);
		assertEquals(vacuumRoom.getId(), id2);
		assertEquals(getSleep.getId(), id3);
	}
	
	@Test
	public void testGetName() {
		assertEquals(createUI.getName(), name1);
		assertEquals(vacuumRoom.getName(), name2);
		assertEquals(getSleep.getName(), name3);
	}
	
	@Test
	public void testGetDescription() {
		assertEquals(createUI.getDescription(), desc1);
		assertEquals(vacuumRoom.getDescription(), desc2);
		assertEquals(getSleep.getDescription(), desc3);
	}
	
	@Test
	public void testGetStartDate() {
		assertEquals(createUI.getStartDate(), startDate1);
		assertEquals(vacuumRoom.getStartDate(), startDate2);
		assertEquals(getSleep.getStartDate(), startDate3);
	}
	
	@Test
	public void testGetDeadline() {
		assertEquals(createUI.getDeadline(), deadline1);
		assertEquals(vacuumRoom.getDeadline(), deadline2);
		assertEquals(getSleep.getDeadline(), deadline3);
	}
	
	@Test
	public void getStatus() {
		assertEquals(createUI.getStatus(), status);
		assertEquals(vacuumRoom.getStatus(), status);
		assertEquals(getSleep.getStatus(), status);
	}
	
	@Test
	public void getTeamAssigned() {
		assertEquals(createUI.getTeamAssigned(), programmers);
		assertEquals(vacuumRoom.getTeamAssigned(), programmers);
		assertEquals(getSleep.getTeamAssigned(), programmers);
	}
	
	@Test
	public void getVotes_list() {
		assertEquals(createUI.getVotes_list().size(), 0);
		Vote vote = new Vote(1, "Because I say so", 1,2, vacuumRoom);
		ArrayList<Vote> votes = new ArrayList<>();
		votes.add(vote);
		vacuumRoom.setVotes_list(votes);
		assertEquals(vacuumRoom.getVotes_list(), votes);
	}
	
	@Test
	public void getReviews_list() {
		assertEquals(createUI.getReviews_list().size(), 0);
		Review review = new Review(1, true, "Very nicely done", 3, 3);
		ArrayList<Review> reviews = new ArrayList<>();
		reviews.add(review);
		vacuumRoom.setReviews_list(reviews);
		assertEquals(vacuumRoom.getReviews_list(), reviews);
	}
	
	@Test
	public void getAssignees_list() {
		assertEquals(createUI.getAssignees_list().size(), 0);
		getSleep.setAssignees_list(assignees);
		assertEquals(getSleep.getAssignees_list(), assignees);
	}

}
