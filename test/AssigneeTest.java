import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class AssigneeTest {
    private static int uniqueId = 0;
    private int id;
    private Assignee assignee;
    private String firstName;
    private String lastName;
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
    private List<Reward> rewards_list = new ArrayList<>();
    private HashSet<Suggestion> suggestions_list = new HashSet<>();
    private HashSet<Team> teams_list = new HashSet<>();


    /**
     * All values are randomized except of uniqueId which is set to 1 because it is the first
     *  and only instance of Help object created for the sake of testing
     */

    @Before
    public void setUp(){
        uniqueId = 1;
        id =5;
        firstName = "Jhon";
        lastName = "Cena";

        stat1 = Status.APPROVED;
        stat2 = Status.ASSIGNED;

        rtype1 = RewardType.BADGE;
        rtype2 = RewardType.TITLE;

        day1 = new Date();
        day2 = new Date();
        day3 = new Date();

        pm = new ProjectManager("Jerycho", "Swain");

        team1 = new Team("Birbs", "Focus on testing", pm, null);
        team2 = new Team("Cats", "Write code", pm, null);
        teams_list.add(team1);
        teams_list.add(team2);

        task1 = new Task("Run", "Just runnin", day1, day2, stat1, team1);
        task2 = new Task("Stop", "Just stoppin", day2, day3, stat2, team2);
        tasks_list.add(task1);
        tasks_list.add(task2);

        rew1 = new Reward("Golden Duck", "Quack", rtype1,day2);
        rew2 = new Reward("Rainbow", "Colorful", rtype2, day3);
        rewards_list.add(rew1);
        rewards_list.add(rew2);

        sug1 = new Suggestion("Code cleanup", "Delete unused imports", 2);
        sug2 = new Suggestion("Add more tests", "We need to test our code", 5);
        suggestions_list.add(sug1);
        suggestions_list.add(sug2);

        assignee = new Assignee(firstName, lastName);
    }

    @Test
    public void getId() {
        assertEquals(id, assignee.getId());
    }

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
        assignee.setFirstName("Dl");

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
    }

    @Test
    public void setTasks_list() {
    }

    @Test
    public void getRewards_list() {
    }

    @Test
    public void setRewards_list() {
    }

    @Test
    public void getSuggestions_list() {
    }

    @Test
    public void setSuggestions_list() {
    }

    @Test
    public void getTeams_list() {
    }

    @Test
    public void setTeams_list() {
    }
}