import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.Assert.*;

public class TaskTest {
    private int id1, id2;
    private String name1, name2;
    private String desc1, desc2;
    private LocalDate startDate1, startDate2;
	private LocalDate endDate1, endDate2;
    private LocalDate deadline1, deadline2;
    private Status status;
    private Team programmers;
	//Two not empty votes lists
	private HashSet<Vote> votes_list = new HashSet<>();
	private HashSet<Vote> votes_list_short = new HashSet<>();
	//Two empty votes lists
	private HashSet<Vote> votes_list2 = new HashSet<>();
	private HashSet<Vote> votes_list_2_short = new HashSet<>();
	//Two not empty reviews lists
	private HashSet<Review> reviews_list = new HashSet<>();
	private HashSet<Review> reviews_list_short = new HashSet<>();
	//Two empty reviews lists
	private HashSet<Review> reviews_list2 = new HashSet<>();
	private HashSet<Review> reviews_list_2_short = new HashSet<>();
	//Two not empty assignees lists
	private HashSet<Assignee> assignees_list = new HashSet<>();
	private HashSet<Assignee> assignees_list_short = new HashSet<>();
	//Two empty assignees lists
	private HashSet<Assignee> assignees_list2 = new HashSet<>();
	private HashSet<Assignee> assignees_list_2_short = new HashSet<>();

    private ProjectManager pm;
	private Task createUI, getSleep;
	
	@Before
	public void setUp() throws StringTooShortException {
		HashSet<Assignee> team_list = new HashSet<>();
		pm = new ProjectManager(1, "Hana", "Busa");
		Assignee a1 = new Assignee(1, "Kuka", "Racza");
		Assignee a2 = new Assignee(2, "Dalai", "Lama");
		team_list.add(pm); team_list.add(a1); team_list.add(a2);
		programmers = new Team(1, "Le Programmers", "Greatest programmers", pm, team_list);
		//members of the team work on tasks
		//those working on the tasks are kept in HashSet<Assignee>
		assignees_list.add(a1); assignees_list.add(a2);
		assignees_list_short.add(a1);

		id1 = 1; id2 = 2;
		name1 = "Create UI"; name2 = "Vacuum your room";
		desc1 = "We need to create a nice UI for our application";
		desc2 = "Be a contributing member of society";
		startDate1 = LocalDate.now(); startDate2 = LocalDate.now();
		endDate1 = LocalDate.now().plus(25, ChronoUnit.DAYS); endDate2 = null;
		deadline1 = LocalDate.now().plus(30, ChronoUnit.DAYS);
		deadline2 = LocalDate.now().plus(60, ChronoUnit.DAYS);
		status = Status.APPROVED;
		createUI = new Task(id1, name1, desc1, startDate1, endDate1, deadline1, status, programmers);
		getSleep = new Task(id2, name2, desc2, startDate2, endDate2, deadline2, status, programmers);

		Vote vote1 = new Vote(1, createUI, "because", 1, 2);
		Vote vote2 = new Vote(2, getSleep, "because", 2, 1);
		Vote vote3 = new Vote(3, createUI, "because", 2, 1);
		votes_list.add(vote1); votes_list.add(vote3);
		votes_list_short.add(vote2);

		Review rev1 = new Review(1, "bhbhbhbhbhbhhbhbhb", true, 1, 1);
		Review rev2 = new Review(2, "bhbhbhbhbhbhhbhbhb", true, 2, 1);
		Review rev3 = new Review(3, "bhbhbhbhbhbhhbhbhb", false, 2, 2);
		reviews_list.add(rev1); reviews_list.add(rev2);
		reviews_list_short.add(rev3);

		createUI.setAssignees_list(assignees_list);
		createUI.setVotes_list(votes_list);
		createUI.setReviews_list(reviews_list);
		getSleep.setAssignees_list(assignees_list_short);
		getSleep.setVotes_list(votes_list_short);
		getSleep.setReviews_list(reviews_list_short);
	}

	//TODO constructor tests

	/**
	 * Get the given values belonging to Task object
	 * Task object values are returned accordingly for each getter
	 * Corner cases: None
	 */
	
	@Test
	public void testGetId() {
		assertEquals(createUI.getId(), id1);
		assertEquals(getSleep.getId(), id2);
	}
	
	@Test
	public void testGetName() {
		assertEquals(createUI.getName(), name1);
		assertEquals(getSleep.getName(), name2);
	}
	
	@Test
	public void testGetDescription() {
		assertEquals(createUI.getDescription(), desc1);
		assertEquals(getSleep.getDescription(), desc2);
	}
	
	@Test
	public void testGetStartDate() {
		assertEquals(createUI.getStartDate(), startDate1);
		assertEquals(getSleep.getStartDate(), startDate2);
	}

	@Test
	public void testGetEndDate() {
		assertEquals(createUI.getEndDate(), endDate1);
		assertEquals(getSleep.getEndDate(), endDate2);
	}
	
	@Test
	public void testGetDeadline() {
		assertEquals(createUI.getDeadline(), deadline1);
		assertEquals(getSleep.getDeadline(), deadline2);
	}
	
	@Test
	public void testGetStatus() {
		assertEquals(createUI.getStatus(), status);
		assertEquals(getSleep.getStatus(), status);
	}

	@Test
	public void testIsVoteStarted() {
		assertFalse(createUI.isVoteStarted());
		assertFalse(getSleep.isVoteStarted());
	}
	
	@Test
	public void testGetTeamAssigned() {
		assertEquals(createUI.getTeamAssigned(), programmers);
		assertEquals(getSleep.getTeamAssigned(), programmers);
	}
	
	@Test
	public void testGetVotes_list() {
		assertEquals(createUI.getVotes_list(), votes_list);
		assertEquals(getSleep.getVotes_list(), votes_list_short);
	}
	
	@Test
	public void testGetReviews_list() {
		assertEquals(createUI.getReviews_list(), reviews_list);
		assertEquals(getSleep.getReviews_list(), reviews_list_short);
	}
	
	@Test
	public void testGetAssignees_list() {
		assertEquals(createUI.getAssignees_list(), assignees_list);
		assertEquals(getSleep.getAssignees_list(), assignees_list_short);
	}

	/**
	 * Set the {attribute} value of Task object
	 * Task object {attribute} is set anew and returned accordingly for each setter
	 * Corner cases:
	 * - values < 0 passed to the id field, null description/name/startDate/deadline/status/teamAssigned
	 * - description.length < 15 / name.length < 3
	 * - startDate is before current date / after endDate / after deadline
	 * - endDate is before current date / before startDate
	 * - deadline is before current date / before startDate
	 */

	@Test
	public void testSetId() {
		assertEquals(id1, createUI.getId());
		createUI.setId(5);
		assertEquals(5, createUI.getId());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetIdToNegative() {
		getSleep.setId(-5);
	}

	@Test
	public void testSetName() throws StringTooShortException {
		assertEquals(name1, createUI.getName());
		createUI.setName("newName");
		assertEquals("newName", createUI.getName());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetNameToNull() throws StringTooShortException {
		getSleep.setName(null);
	}

	@Test(expected=StringTooShortException.class)
	public void testSetNameStringTooShort() throws StringTooShortException {
		getSleep.setName("H");
	}

	@Test
	public void testSetDescription() throws StringTooShortException {
		assertEquals(desc1, createUI.getDescription());
		createUI.setDescription("newDescriptionnnnnnnn");
		assertEquals("newDescriptionnnnnnnn", createUI.getDescription());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetDescriptionToNull() throws StringTooShortException {
		getSleep.setDescription(null);
	}

	@Test(expected=StringTooShortException.class)
	public void testSetDescriptionStringTooShort() throws StringTooShortException {
		getSleep.setDescription("descr");
	}

	@Test
	public void testSetStartDate() {
		assertEquals(startDate1, createUI.getStartDate());
		LocalDate tmp = LocalDate.now().plus(10, ChronoUnit.DAYS);
		createUI.setStartDate(LocalDate.now().plus(10, ChronoUnit.DAYS));
		assertEquals(tmp, createUI.getStartDate());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetStartDateIllegalArgument() {
		LocalDate nullDate = null;
		LocalDate pastDate = LocalDate.now().minus(1, ChronoUnit.DAYS);
		LocalDate afterEndDate = createUI.getEndDate().plus(1, ChronoUnit.DAYS);
		LocalDate afterDeadline = createUI.getDeadline().plus(1, ChronoUnit.DAYS);
		createUI.setStartDate(nullDate);
		createUI.setStartDate(pastDate);
		createUI.setStartDate(afterEndDate);
		createUI.setStartDate(afterDeadline);
	}

	@Test
	public void testSetEndDate() {
		assertEquals(endDate1, createUI.getEndDate());
		LocalDate tmp = LocalDate.now().plus(10, ChronoUnit.DAYS);
		createUI.setEndDate(LocalDate.now().plus(10, ChronoUnit.DAYS));
		assertEquals(tmp, createUI.getEndDate());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetEndDateIllegalArgument() {
		LocalDate pastDate = LocalDate.now().minus(1, ChronoUnit.DAYS);
		LocalDate beforeStartDate = createUI.getStartDate().minus(1, ChronoUnit.DAYS);
		createUI.setEndDate(pastDate);
		createUI.setEndDate(beforeStartDate);
	}

	@Test
	public void testSetDeadline() {
		assertEquals(deadline1, createUI.getDeadline());
		LocalDate tmp = LocalDate.now().plus(10, ChronoUnit.DAYS);
		createUI.setDeadline(LocalDate.now().plus(10, ChronoUnit.DAYS));
		assertEquals(tmp, createUI.getDeadline());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetDeadlineIllegalArgument() {
		LocalDate nullDate = null;
		LocalDate pastDate = LocalDate.now().minus(1, ChronoUnit.DAYS);
		LocalDate beforeStartDate = createUI.getStartDate().minus(1, ChronoUnit.DAYS);
		createUI.setDeadline(nullDate);
		createUI.setDeadline(pastDate);
		createUI.setDeadline(beforeStartDate);
	}

	@Test
	public void testSetStatus() {
		assertEquals(status, createUI.getStatus());
		createUI.setStatus(Status.UNDER_REVIEW);
		assertEquals(Status.UNDER_REVIEW, createUI.getStatus());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetStatusToNull() {
		getSleep.setStatus(null);
	}

	@Test
	public void testSetVoteStarted() {
		assertFalse(createUI.isVoteStarted());
		createUI.setVoteStarted(true);
		assertTrue(createUI.isVoteStarted());
	}

	@Test
	public void testSetTeamAssigned() throws StringTooShortException {
		assertEquals(programmers, createUI.getTeamAssigned());
		Team goodTeam = new Team(2, "Le Testers", "Greatest testers", pm, assignees_list);
		createUI.setTeamAssigned(goodTeam);
		assertEquals(goodTeam, createUI.getTeamAssigned());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetTeamAssignedToNull() {
		getSleep.setTeamAssigned(null);
	}

	/**
	 * Set the {objects}_list value of Task object
	 * Task object {objects}_list is set anew and returned accordingly for each setter
	 * Cases:  1) The {objects}_list in Task is not empty and is set to a new empty list
	 *         2) The {objects}_list in Task is empty and is set to a new not empty list
	 *         3) The {objects}_list in Task is empty and is set to a new empty list
	 *         4) The {objects}_list in Task is not empty and is set to a new not empty list
	 * Corner cases:
	 * - input list is null
	 * - for Assignee_list: assignees being assigned are not part of the team, working on this task
	 * - for Reviews_list: reviews of different tasks, then the given one
	 */

	//TODO check -- if it says not empty to empty - is it really that way??
	//check in other classes too, fix if wrong

	//TODO complete list tests

	@Test
	public void testSetAssignees_listFromNotEmptyToEmpty() {
		createUI.setAssignees_list(assignees_list2);
		assertEquals(assignees_list2, createUI.getAssignees_list());
	}

	@Test
	public void testSetAssignees_listFromEmptyToNotEmpty() {
		getSleep.setAssignees_list(assignees_list);
		assertEquals(assignees_list, getSleep.getAssignees_list());
	}

	@Test
	public void testSetAssignees_listFromEmptyToEmpty() {
		getSleep.setAssignees_list(assignees_list_2_short);
		assertEquals(assignees_list_2_short, getSleep.getAssignees_list());
	}

	@Test
	public void testSetAssignees_listFromNotEmptyToNotEmpty() {
		createUI.setAssignees_list(assignees_list_short);
		assertEquals(assignees_list_short, createUI.getAssignees_list());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetAssignees_listToNull() {
		createUI.setAssignees_list(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetAssignees_listToRandomAssignees() throws StringTooShortException {
		Assignee a3 = new Assignee(1, "Me3", "Worker3");
		Assignee a4 = new Assignee(2, "Me4", "Worker4");
		HashSet<Assignee> list = new HashSet<>();
		list.add(a3); list.add(a4);
		createUI.setAssignees_list(list);
		assertEquals(list, createUI.getAssignees_list());
	}

	@Test
	public void testSetVotes_listFromNotEmptyToEmpty() {
		createUI.setVotes_list(votes_list2);
		assertEquals(votes_list2, createUI.getVotes_list());
	}

	@Test
	public void testSetVotes_listFromEmptyToNotEmpty() {
		getSleep.setVotes_list(votes_list);
		assertEquals(votes_list, getSleep.getVotes_list());
	}

	@Test
	public void testSetVotes_listFromEmptyToEmpty() {
		getSleep.setVotes_list(votes_list_2_short);
		assertEquals(votes_list_2_short, getSleep.getVotes_list());
	}

	@Test
	public void testSetVotes_listFromNotEmptyToNotEmpty() {
		createUI.setVotes_list(votes_list_short);
		assertEquals(votes_list_short, createUI.getVotes_list());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetVotes_listToNull() {
		createUI.setVotes_list(null);
	}





/*
	@Test
	public void testSetVotes_list() throws StringTooShortException {
		Vote goodVote = new Vote(1, createUI, "Because yes", 1, 2);
		Vote badVote = new Vote(2, getSleep, "Yes because", 1, 2);
		HashSet<Vote> votes = new HashSet<>();
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
	public void testSetReviews_list() throws StringTooShortException {
		Review review = new Review(1,"Very nicely done", true, 3, 3);
		HashSet<Review> reviews = new HashSet<>();
		reviews.add(review);
		getSleep.setReviews_list(reviews);
		assertEquals(getSleep.getReviews_list(), reviews);
		try {
			createUI.setReviews_list(reviews);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "All reviews in the list should belong to this task.");
		}
		try {
			createUI.setReviews_list(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Argument cannot be null");
		}
	}

	@Test
	public void testSetAssignees_list() throws StringTooShortException {
		Assignee badAssignee = new Assignee(4, "Voldo", "Italian");
		HashSet<Assignee> badAssignees = new HashSet<>();
		badAssignees.add(badAssignee);
		try {
			createUI.setAssignees_list(badAssignees);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Assignees in the list should belong to this task's team.");
		}

		HashSet<Assignee> goodAssignees = assignees_list;
		getSleep.setAssignees_list(goodAssignees);

		try {
			getSleep.setAssignees_list(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Argument cannot be null");
		}
	}

 */
}
