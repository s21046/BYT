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
	public void testGetStatus() {
		assertEquals(createUI.getStatus(), status);
		assertEquals(vacuumRoom.getStatus(), status);
		assertEquals(getSleep.getStatus(), status);
	}

	@Test
	public void testIsVoteStarted() {
		assertEquals(createUI.isVoteStarted(), false);
		assertEquals(vacuumRoom.isVoteStarted(), false);
		assertEquals(getSleep.isVoteStarted(), false);
	}
	
	@Test
	public void testGetTeamAssigned() {
		assertEquals(createUI.getTeamAssigned(), programmers);
		assertEquals(vacuumRoom.getTeamAssigned(), programmers);
		assertEquals(getSleep.getTeamAssigned(), programmers);
	}
	
	@Test
	public void testGetVotes_list() {
		assertEquals(createUI.getVotes_list().size(), 0);
		Vote vote = new Vote(1, "Because I say so", 1,2, vacuumRoom);
		ArrayList<Vote> votes = new ArrayList<>();
		votes.add(vote);
		vacuumRoom.setVotes_list(votes);
		assertEquals(vacuumRoom.getVotes_list(), votes);
	}
	
	@Test
	public void testGetReviews_list() {
		assertEquals(createUI.getReviews_list().size(), 0);
		Review review = new Review(1, true, "Very nicely done", 3, 3);
		ArrayList<Review> reviews = new ArrayList<>();
		reviews.add(review);
		vacuumRoom.setReviews_list(reviews);
		assertEquals(vacuumRoom.getReviews_list(), reviews);
	}
	
	@Test
	public void testGetAssignees_list() {
		assertEquals(createUI.getAssignees_list().size(), 0);
		getSleep.setAssignees_list(assignees);
		assertEquals(getSleep.getAssignees_list(), assignees);
	}

	/**
	 * Set the given values to fields belonging to Help object
	 * @result Help object values are set anew and returned accordingly for each setter
	 * @corner_cases
	 * Methods are tested on the matter of invalid input id
	 * 	setId: negative argument,
	 * 	setName: null argument, name shorter than 3 characters
	 * 	setDescription: null argument, description shorter than 15 characters
	 * 	setStartDate: null argument, date in the past, date after deadline
	 * 	setDeadline: null argument, date in the past, date before startDate
	 * 	setStatus: null argument
	 * 	setTeamAssigned: null argument
	 * 	setVotes_list: null argument,
	 * 	setReviews_list: null argument, Review lists containing Reviews for different Tasks
	 * 	setAssignees_list: null argument, Assignee lists containing Assignees belonging to a different Team than the Task is assigned to
	 */

	@Test(expected=IllegalArgumentException.class)
	public void testSetId() {
		vacuumRoom.setId(10);
		assertEquals(vacuumRoom.getId(), 10);
		createUI.setId(-4);
	}

	@Test
	public void testSetName() throws StringTooShortException {
		String newName1 = "Create a pleasing UI";
		createUI.setName(newName1);
		try {
			String newName2 = "UI";
			createUI.setName(newName2);
			fail();
		}
		catch (StringTooShortException e) {
			// should be thrown
		}

		try {
			String newName3 = null;
			vacuumRoom.setName(newName3);
			fail();
		} catch (IllegalArgumentException e) {
			// should be thrown
		}
	}

	@Test
	public void testSetDescription() throws StringTooShortException {
		String newDesc1 = "We really love pleasing UIs! They are so cool!";
		createUI.setDescription(newDesc1);
		try {
			String newDesc2 = "Do it";
			createUI.setDescription(newDesc2);
			fail();
		}
		catch (StringTooShortException e) {
			// should be thrown
		}

		try {
			String newDesc3 = null;
			vacuumRoom.setDescription(newDesc3);
			fail();
		} catch (IllegalArgumentException e) {
			// should be thrown
		}
	}

	@Test
	public void testSetStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(createUI.getStartDate());
		cal.add(Calendar.DATE, -30);
		Date badStartDate = cal.getTime();
		try {
			createUI.setStartDate(badStartDate);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Start date cannot be in past.");
		}

		Date badStartDate2 = new Date();
		try {
			vacuumRoom.setStartDate(badStartDate2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Start date cannot be after the deadline");
		}

		Date badStartDate3 = null;
		try {
			vacuumRoom.setStartDate(badStartDate3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Argument cannot be null");
		}
	}

	@Test
	public void testSetDeadline() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(createUI.getStartDate());
		cal.add(Calendar.DATE, -30);
		Date badDeadline = cal.getTime();
		try {
			createUI.setDeadline(badDeadline);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Deadline cannot be in past.");
		}

		cal.add(Calendar.DATE, 60);
		Date temp = cal.getTime();
		createUI.setDeadline(temp);
		createUI.setStartDate(temp);
		cal.add(Calendar.DATE, -10);
		Date badDeadline2 = cal.getTime();
		try {
			createUI.setDeadline(badDeadline2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Deadline cannot precede the start date.");
		}

		Date badDeadline3 = null;
		try {
			vacuumRoom.setDeadline(badDeadline3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Argument cannot be null");
		}
	}


	@Test
	public void testSetStatus() {
		createUI.setStatus(Status.ASSIGNED);
		assertEquals(createUI.getStatus(), Status.ASSIGNED);

		try {
			getSleep.setStatus(null);
			fail();
		} catch (IllegalArgumentException e) {
			// should be thrown
		}
	}

	@Test
	public void testSetVoteStarted() {
		createUI.setVoteStarted(true);
		assertTrue(createUI.isVoteStarted());
	}

	@Test
	public void testSetTeamAssigned() {
		try {
			Team badTeam = null;
			createUI.setTeamAssigned(badTeam);
			fail();
		} catch (IllegalArgumentException e) {
			// should be thrown
		}

		Team goodTeam = new Team(2, "Le Testers", "Greatest testers", pm, assignees);
		vacuumRoom.setTeamAssigned(goodTeam);
	}

	@Test
	public void testSetVotes_list() {
		Vote goodVote = new Vote(1, "Because yes", 1, 2, createUI);
		Vote badVote = new Vote(2, "Yes because", 1, 2, vacuumRoom);
		ArrayList<Vote> votes = new ArrayList<>();
		votes.add(goodVote);
		createUI.setVotes_list(votes);
		votes.add(badVote);
		try {
			createUI.setVotes_list(votes);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "All votes in the list should belong to this task.");
		}
		votes = null;
		try {
			createUI.setVotes_list(votes);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Argument cannot be null");
		}
	}

	@Test
	public void testSetReviews_list() {
		Review review = new Review(1, true, "Very nicely done", 3, 3);
		ArrayList<Review> reviews = new ArrayList<>();
		reviews.add(review);
		getSleep.setReviews_list(reviews);
		assertEquals(getSleep.getReviews_list(), reviews);
		try {
			vacuumRoom.setReviews_list(reviews);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "All reviews in the list should belong to this task.");
		}
		try {
			getSleep.setReviews_list(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Argument cannot be null");
		}
	}

	@Test
	public void testSetAssignees_list() {
		Assignee badAssignee = new Assignee(4, "Voldo", "Italian");
		ArrayList<Assignee> badAssignees = new ArrayList<>();
		badAssignees.add(badAssignee);
		try {
			createUI.setAssignees_list(badAssignees);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Assignees in the list should belong to this task's team.");
		}

		ArrayList<Assignee> goodAssignees = (ArrayList<Assignee>) assignees;
		vacuumRoom.setAssignees_list(goodAssignees);

		try {
			getSleep.setAssignees_list(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Argument cannot be null");
		}
	}

}
