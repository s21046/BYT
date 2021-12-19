import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class AssigneeTest {
    private int id;
    private int id1;
    private Assignee assignee;
    private Assignee assignee1;
    private String firstName;
    private String lastName;
    private String firstName1;
    private String lastName1;
    private ProjectManager pm;
    private Date day1;
    private Date day2;
    private Date day3;
    private Task task1;
    private Task task2;
    private Reward rew1;
    private Reward rew2;
    private RewardType rtype1;
    private RewardType rtype2;
    private Status stat1;
    private Status stat2;
    private Suggestion sug1;
    private Suggestion sug2;
    private Team team1;
    private Team team2;
    private List<Task> tasks_list = new ArrayList<>();
    private List<Task> tasks_list2 = new ArrayList<>();
    private List<Reward> rewards_list = new ArrayList<>();
    private List<Reward> rewards_list2 = new ArrayList<>();
    private HashSet<Suggestion> suggestions_list = new HashSet<>();
    private HashSet<Suggestion> suggestions_list2 = new HashSet<>();
    private HashSet<Team> teams_list = new HashSet<>();
    private HashSet<Team> teams_list2 = new HashSet<>();


    /**
     * All values are randomized except of uniqueId which is set to 1 because it is the first
     *  and only instance of Help object created for the sake of testing
     */

    @Before
    public void setUp() {
        id =11;
        id1 = 12;
        firstName = "Jhon";
        firstName1 = "Adam";
        lastName = "Cena";
        lastName1 = "Stanowski";

        stat1 = Status.APPROVED;
        stat2 = Status.ASSIGNED;

        rtype1 = RewardType.BADGE;
        rtype2 = RewardType.TITLE;

        day1 = new Date();
        day2 = new Date();
        day3 = new Date();

        pm = new ProjectManager(2,"Jerycho", "Swain");

        team1 = new Team(1,"Birbs", "Focus on testing", pm, null);
        team2 = new Team(2,"Cats", "Write code", pm, null);
        teams_list.add(team1);
        teams_list.add(team2);

        task1 = new Task(1,"Run", "Just runnin", day1, day2, stat1, team1);
        task2 = new Task( 2,"Stop", "Just stoppin", day2, day3, stat2, team2);
        tasks_list.add(task1);
        tasks_list.add(task2);

        rew1 = new Reward(3,"Golden Duck", "Quack", rtype1,day2);
        rew2 = new Reward( 4, "Rainbow", "Colorful", rtype2, day3);
        rewards_list.add(rew1);
        rewards_list.add(rew2);

        sug1 = new Suggestion(1,"Code cleanup", "Delete unused imports", 2);
        sug2 = new Suggestion(2, "Add more tests", "We need to test our code", 5);
        suggestions_list.add(sug1);
        suggestions_list.add(sug2);

        assignee = new Assignee(id, firstName, lastName);
        assignee.setTasks_list(tasks_list);
        assignee.setSuggestions_list(suggestions_list);
        assignee.setRewards_list(rewards_list);
        assignee.setTeams_list(teams_list);

        assignee1 = new Assignee(id1, firstName1,lastName1);
        assignee1.setTasks_list(tasks_list2);
        assignee1.setSuggestions_list(suggestions_list2);
        assignee1.setRewards_list(rewards_list2);
        assignee1.setTeams_list(teams_list2);
    }

    @Test
    public void getId() {
        assertEquals(11, assignee.getId());
    }

    //FIXXXXXXXX - add tests for id being unique
    @Test
    public void setId() {
        assignee.setId(14);
        assertEquals(14, assignee.getId());
    }

    @Test
    public void getFirstName() {
        assertEquals(firstName, assignee.getFirstName());
    }

    @Test
    public void setFirstName() throws StringTooShortException {
        assignee.setFirstName("Bobinator");
        assertEquals("Bobinator", assignee.getFirstName());

    }

    @Test(expected=StringTooShortException.class)
    public void setFirstNameTooShortException() throws StringTooShortException {
        assignee.setFirstName("");

    }

    @Test(expected=IllegalArgumentException.class)
    public void setFirstNameNullException() throws StringTooShortException {
        assignee.setFirstName(null);
    }

    @Test
    public void getLastName() {
        assertEquals(lastName, assignee.getLastName());
    }

    @Test
    public void setLastName() throws StringTooShortException {
        assignee.setLastName("Jason");
        assertEquals("Jason", assignee.getLastName());
    }

    @Test(expected=StringTooShortException.class)
    public void setLastNameTooShortException() throws StringTooShortException {
        assignee.setFirstName("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void setLastNameNullException() throws StringTooShortException {
        assignee.setFirstName(null);
    }

    @Test
    public void getTasks_list() {
        assertEquals(tasks_list, assignee.getTasks_list());
    }

    @Test
    public void setTasks_listToEmpty() {
        assignee.setTasks_list(tasks_list2);
        assertEquals(tasks_list2, assignee.getTasks_list());
    }

    @Test
    public void setTasks_listToNotEmpty() {
        assignee1.setTasks_list(tasks_list);
        assertEquals(tasks_list, assignee1.getTasks_list());
    }

    @Test(expected=IllegalArgumentException.class)
    public void setTasks_listIsNullException() {
        assignee.setTasks_list(null);
    }

    @Test
    public void getRewards_list() {
        assertEquals(rewards_list, assignee.getRewards_list());
    }

    @Test
    public void setRewards_listToEmpty() {
        assignee.setRewards_list(rewards_list2);
        assertEquals(rewards_list2, assignee.getRewards_list());
    }

    @Test
    public void setRewards_listToNotEmpty() {
        assignee1.setRewards_list(rewards_list);
        assertEquals(rewards_list, assignee1.getRewards_list());
    }


    @Test(expected=IllegalArgumentException.class)
    public void getRewards_listIsNullException() {
        assignee.setRewards_list(null);
    }

    @Test
    public void getSuggestions_list() {
        assertEquals(suggestions_list, assignee.getSuggestions_list());
    }

    @Test
    public void setSuggestions_listToEmpty() {
        assignee.setSuggestions_list(suggestions_list2);
        assertEquals(suggestions_list2, assignee.getSuggestions_list());
    }

    @Test
    public void setSuggestions_listToNotEmpty() {
        assignee1.setSuggestions_list(suggestions_list);
        assertEquals(suggestions_list, assignee1.getSuggestions_list());
    }

    @Test
    public void getTeams_list() {
        assertEquals(teams_list, assignee.getTeams_list());
    }

    @Test
    public void setTeams_listToEmpty() {
        assignee.setTeams_list(teams_list2);
        assertEquals(teams_list2, assignee.getTeams_list());
    }

    @Test
    public void setTeams_listToNotEmpty() {
        assignee1.setTeams_list(teams_list);
        assertEquals(teams_list, assignee1.getTeams_list());
    }

}