import ApplicationExceptions.StringTooShortException;
import ApplicationExceptions.ValueAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.Assert.*;

public class TaskTest {
    private String name1, name2;
    private String desc1, desc2;
    private LocalDate startDate1, startDate2;
	private LocalDate endDate1, endDate2;
    private LocalDate deadline1, deadline2;
    private Status status;
    private Team programmers;
	//Three not empty votes lists
	private HashSet<Vote> votes_list = new HashSet<>();
	private HashSet<Vote> votes_list_short = new HashSet<>();
	private HashSet<Vote> votes_list_long = new HashSet<>();
	//Two empty votes lists
	private HashSet<Vote> votes_list2 = new HashSet<>();
	private HashSet<Vote> votes_list_2_short = new HashSet<>();
	//Three not empty reviews lists
	private HashSet<Review> reviews_list = new HashSet<>();
	private HashSet<Review> reviews_list_short = new HashSet<>();
	private HashSet<Review> reviews_list_long = new HashSet<>();
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
	UniqueIdGenerator<Assignee> assigneeUniqueIdGenerator= new UniqueIdGenerator<>();
	UniqueIdGenerator<Team> teamUniqueIdGenerator= new UniqueIdGenerator<>();
	UniqueIdGenerator<Reward> rewardUniqueIdGenerator= new UniqueIdGenerator<>();
	UniqueIdGenerator<Task> taskUniqueIdGenerator= new UniqueIdGenerator<>();
	UniqueIdGenerator<Suggestion> suggestionUniqueIdGenerator= new UniqueIdGenerator<>();
	UniqueIdGenerator<Review> reviewUniqueIdGenerator= new UniqueIdGenerator<>();
	UniqueIdGenerator<Vote> voteUniqueIdGenerator= new UniqueIdGenerator<>();
	@Before
	public void setUp() throws StringTooShortException, ValueAlreadyExistsException {

		HashSet<Assignee> team_list = new HashSet<>();

		pm = new ProjectManager( "Hana", "Busa");
		pm.setId(assigneeUniqueIdGenerator.generateId(pm));
		Assignee a1 = new Assignee( "Kuka", "Racza");
		a1.setId(assigneeUniqueIdGenerator.generateId(a1));
		Assignee a2 = new Assignee( "Dalai", "Lama");
		a2.setId(assigneeUniqueIdGenerator.generateId(a2));
		team_list.add(pm); team_list.add(a1); team_list.add(a2);
		programmers = new Team( "Le Programmers", "Greatest programmers", pm, team_list);
		//members of the team work on tasks
		//those working on the tasks are kept in HashSet<Assignee>
		assignees_list.add(a1); assignees_list.add(a2);
		assignees_list_short.add(a1);

		name1 = "Create UI"; name2 = "Get some sleep";
		desc1 = "We need to create a nice UI for our application";
		desc2 = "Everyone needs some rest every now and then";
		startDate1 = LocalDate.now(); startDate2 = LocalDate.now();
		endDate1 = LocalDate.now().plus(25, ChronoUnit.DAYS); endDate2 = null;
		deadline1 = LocalDate.now().plus(30, ChronoUnit.DAYS);
		deadline2 = LocalDate.now().plus(60, ChronoUnit.DAYS);
		status = Status.APPROVED;
		createUI = new Task( name1, desc1, startDate1, endDate1, deadline1, status, programmers);
		createUI.setId(taskUniqueIdGenerator.generateId(createUI));
		getSleep = new Task( name2, desc2, startDate2, endDate2, deadline2, status, programmers);
		getSleep.setId(taskUniqueIdGenerator.generateId(getSleep));

		Vote vote1 = new Vote( createUI, "because", 0, 1);
		vote1.setId(voteUniqueIdGenerator.generateId(vote1));
		Vote vote2 = new Vote( getSleep, "because", 1, 0);
		vote2.setId(voteUniqueIdGenerator.generateId(vote2));
		Vote vote3 = new Vote( createUI, "because", 1, 0);
		vote3.setId(voteUniqueIdGenerator.generateId(vote3));
		Vote vote4 = new Vote( createUI, "because!", 2,2);
		vote4.setId(voteUniqueIdGenerator.generateId(vote4));
		votes_list.add(vote1); votes_list.add(vote3);
		votes_list_short.add(vote2);
		votes_list_long.add(vote1); votes_list_long.add(vote3); votes_list_long.add(vote4);

		Review rev1 = new Review( "bhbhbhbhbhbhhbhbhb", true, 0, 0);
		rev1.setId(reviewUniqueIdGenerator.generateId(rev1));
		Review rev2 = new Review( "bhbhbhbhbhbhhbhbhb", true, 1, 0);
		rev2.setId(reviewUniqueIdGenerator.generateId(rev2));
		Review rev3 = new Review( "bhbhbhbhbhbhhbhbhb", false, 1, 1);
		rev3.setId(reviewUniqueIdGenerator.generateId(rev3));
		Review rev4 = new Review( "baabbabababababa", false, 2, 0);
		rev4.setId(reviewUniqueIdGenerator.generateId(rev4));
		reviews_list.add(rev1); reviews_list.add(rev2);
		reviews_list_short.add(rev3);
		reviews_list_long.add(rev1); reviews_list_long.add(rev2); reviews_list_long.add(rev4);

		createUI.setAssignees_list(assignees_list);
		createUI.setVotes_list(votes_list);
		createUI.setReviews_list(reviews_list);
		getSleep.setAssignees_list(assignees_list2);
		getSleep.setVotes_list(votes_list2);
		getSleep.setReviews_list(reviews_list2);
	}

	/**
	 * Create a Task object
	 * Returns a Task object according to the parameters
	 * Corner cases:
	 * - values < 0 passed to the id field, null description/name/startDate/deadline/status/teamAssigned
	 * - description.length < 15 / name.length < 3
	 * - startDate is before current date / after endDate / after deadline
	 * - endDate is before current date / before startDate
	 * - deadline is before current date / before startDate
	 */

	@Test
	public void testConstructor() throws StringTooShortException {
		new Task( "Tasky task", "Appropriate description",
				LocalDate.now(), null, LocalDate.now().plusDays(1),
				Status.APPROVED, programmers);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithNullFields() throws StringTooShortException {
		new Task( "Tasky task", null,
				LocalDate.now(), null, null,
				Status.APPROVED, null);
	}

	@Test(expected=StringTooShortException.class)
	public void testConstructorWithShortName() throws StringTooShortException {
		new Task( "T", "Appropriate description",
				LocalDate.now(), null, LocalDate.now().plusDays(1),
				Status.APPROVED, programmers);
	}

	@Test(expected=StringTooShortException.class)
	public void testConstructorWithShortDescription() throws StringTooShortException {
		new Task( "Task", "Huh?",
				LocalDate.now(), null, LocalDate.now().plusDays(1),
				Status.APPROVED, programmers);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithStartDateAfterDeadline() throws StringTooShortException {
		new Task( "Task", "Appropriate description",
				LocalDate.now().plusDays(1), null, LocalDate.now(),
				Status.APPROVED, programmers);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithStartDateInPast() throws StringTooShortException {
		new Task( "Task", "Appropriate description",
				LocalDate.now().minusDays(1), null, LocalDate.now(),
				Status.APPROVED, programmers);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithEndDateBeforeStartDate() throws StringTooShortException {
		new Task( "Task", "Appropriate description",
				LocalDate.now(), LocalDate.now().minusDays(1), LocalDate.now().plusDays(1),
				Status.APPROVED, programmers);
	}

	/**
	 * Get the given values belonging to Task object
	 * Task object values are returned accordingly for each getter
	 * Corner cases: None
	 */
	
	@Test
	public void testGetId() {
		assertEquals(createUI.getId(), 0);
		assertEquals(getSleep.getId(), 1);
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
		assertEquals(getSleep.getVotes_list(), votes_list2);
	}
	
	@Test
	public void testGetReviews_list() {
		assertEquals(createUI.getReviews_list(), reviews_list);
		assertEquals(getSleep.getReviews_list(), reviews_list2);
	}
	
	@Test
	public void testGetAssignees_list() {
		assertEquals(createUI.getAssignees_list(), assignees_list);
		assertEquals(getSleep.getAssignees_list(), assignees_list2);
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
		assertEquals(0, createUI.getId());
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
		Team goodTeam = new Team( "Le Testers", "Greatest testers", pm, assignees_list);
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
	 * - for Assignee_list: assignees being assigned are not part of the team working on this task
	 * - for Votes_list, Reviews_list: reviews of different tasks than the given one
	 */

	@Test
	public void testSetAssignees_listFromNotEmptyToEmpty() {
		assertFalse(createUI.getAssignees_list().isEmpty());
		createUI.setAssignees_list(assignees_list2);
		assertEquals(assignees_list2, createUI.getAssignees_list());
	}

	@Test
	public void testSetAssignees_listFromEmptyToNotEmpty() {
		assertTrue(getSleep.getAssignees_list().isEmpty());
		getSleep.setAssignees_list(assignees_list);
		assertEquals(assignees_list, getSleep.getAssignees_list());
	}

	@Test
	public void testSetAssignees_listFromEmptyToEmpty() {
		assertTrue(getSleep.getAssignees_list().isEmpty());
		getSleep.setAssignees_list(assignees_list_2_short);
		assertEquals(assignees_list_2_short, getSleep.getAssignees_list());
	}

	@Test
	public void testSetAssignees_listFromNotEmptyToNotEmpty() {
		assertFalse(createUI.getAssignees_list().isEmpty());
		createUI.setAssignees_list(assignees_list_short);
		assertEquals(assignees_list_short, createUI.getAssignees_list());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetAssignees_listToNull() {
		createUI.setAssignees_list(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetAssignees_listToRandomAssignees() throws StringTooShortException {
		Assignee a3 = new Assignee("Me3", "Worker3");
		Assignee a4 = new Assignee( "Me4", "Worker4");
		HashSet<Assignee> list = new HashSet<>();
		list.add(a3); list.add(a4);
		createUI.setAssignees_list(list);
		assertEquals(list, createUI.getAssignees_list());
	}

	@Test
	public void testSetVotes_listFromNotEmptyToEmpty() {
		assertFalse(createUI.getVotes_list().isEmpty());
		createUI.setVotes_list(votes_list2);
		assertEquals(votes_list2, createUI.getVotes_list());
	}

	@Test
	public void testSetVotes_listFromEmptyToNotEmpty() {
		assertTrue(getSleep.getVotes_list().isEmpty());
		getSleep.setVotes_list(votes_list_short);
		assertEquals(votes_list_short, getSleep.getVotes_list());
	}

	@Test
	public void testSetVotes_listFromEmptyToEmpty() {
		assertTrue(getSleep.getVotes_list().isEmpty());
		getSleep.setVotes_list(votes_list_2_short);
		assertEquals(votes_list_2_short, getSleep.getVotes_list());
	}

	@Test
	public void testSetVotes_listFromNotEmptyToNotEmpty() {
		assertFalse(createUI.getVotes_list().isEmpty());
		createUI.setVotes_list(votes_list_long);
		assertEquals(votes_list_long, createUI.getVotes_list());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetVotes_listToNull() {
		createUI.setVotes_list(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetVotes_listToDifferentTask(){
		createUI.setVotes_list(votes_list_short);
	}

	@Test
	public void testSetReviews_listFromNotEmptyToEmpty() {
		assertFalse(createUI.getReviews_list().isEmpty());
		createUI.setReviews_list(reviews_list2);
		assertEquals(reviews_list2, createUI.getReviews_list());
	}

	@Test
	public void testSetReviews_listFromEmptyToNotEmpty() {
		assertTrue(getSleep.getReviews_list().isEmpty());
		getSleep.setReviews_list(reviews_list_short);
		assertEquals(reviews_list_short, getSleep.getReviews_list());
	}

	@Test
	public void testSetReviews_listFromEmptyToEmpty() {
		assertTrue(getSleep.getReviews_list().isEmpty());
		getSleep.setReviews_list(reviews_list_2_short);
		assertEquals(reviews_list_2_short, getSleep.getReviews_list());
	}

	@Test
	public void testSetReviews_listFromNotEmptyToNotEmpty() {
		assertFalse(createUI.getReviews_list().isEmpty());
		createUI.setReviews_list(reviews_list_long);
		assertEquals(reviews_list_long, createUI.getReviews_list());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetReviews_listToNull() {
		createUI.setReviews_list(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetReviews_listToDifferentTask(){
		createUI.setReviews_list(reviews_list_short);
	}
}
