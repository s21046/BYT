import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class RewardTest {
    private int id;
    private String name;
    private String description;
    private RewardType type;
    private LocalDate dateGiven;
    private Reward reward;

    @Before
    public void setUp() throws StringTooShortException {
        id = 1;
        name = "Best Reward";
        description = "Just for testing purposes";
        type = RewardType.BADGE;
        dateGiven = LocalDate.now();
        reward = new Reward(1, name, description, type, dateGiven);
    }

    /**
     * Initiate constructor with various values
     * Corner cases:
     * - values < 0 passed to the id field, null description/name/type/dateGiven, date from the future
     * - description.length < 15 / name.length < 3
     */

    @Test
    public void testConstructor() throws StringTooShortException {
        new Reward(1, "Name", "BIIIIIIiiiiiigggggggg", RewardType.TITLE, LocalDate.now());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorIllegalArguments() throws StringTooShortException {
        new Reward(-1, null, null, null, LocalDate.now().plus(10, ChronoUnit.DAYS));
        new Reward(-1, null, null, null, null);
    }

    @Test(expected=StringTooShortException.class)
    public void testConstructorStringTooShort() throws StringTooShortException {
        new Reward(0, "N", "", RewardType.BADGE, LocalDate.now());
    }

    /**
     * Get the given values belonging to Reward object
     * Reward object values are returned accordingly for each getter
     * Corner cases: None
     */

    @Test
    public void testGetId() {
        assertEquals(id, reward.getId());
    }

    @Test
    public void testGetName() {
        assertEquals(name, reward.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals(description, reward.getDescription());
    }

    @Test
    public void testGetType() {
        assertEquals(type, reward.getType());
    }

    @Test
    public void testGetDateGiven() {
        assertEquals(dateGiven, reward.getDateGiven());
    }

    /**
     * Set the {attribute} value of Reward object
     * Reward object {attribute} is set anew and returned accordingly for each setter
     * Corner cases:
     * - values < 0 passed to the id field, null description/name/type/dateGiven, date from the future
     * - description.length < 15 / name.length < 3
     */

    @Test
    public void testSetId() {
        assertEquals(1, reward.getId());
        reward.setId(15);
        assertEquals(15, reward.getId());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetIdToNegative() {
        reward.setId(-2);
    }

    @Test
    public void testSetName() throws StringTooShortException {
        assertEquals("Best Reward", reward.getName());
        reward.setName("FFF");
        assertEquals("FFF", reward.getName());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetNameToNull() throws StringTooShortException {
        reward.setName(null);
    }

    @Test(expected=StringTooShortException.class)
    public void testSetNameStringTooShort() throws StringTooShortException {
        reward.setName("N");
    }

    @Test
    public void testSetDescription() throws StringTooShortException {
        assertEquals(description, reward.getDescription());
        reward.setDescription("Very gooooooooooood");
        assertEquals("Very gooooooooooood", reward.getDescription());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDescriptionToNull() throws StringTooShortException {
        reward.setDescription(null);
    }

    @Test(expected=StringTooShortException.class)
    public void testSetDescriptionStringTooShort() throws StringTooShortException {
        reward.setDescription("Descr");
    }

    @Test
    public void testSetType() {
        assertEquals(type, reward.getType());
        reward.setType(RewardType.TITLE);
        assertEquals(RewardType.TITLE, reward.getType());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetTypeToNull() {
        reward.setType(null);
    }

    @Test
    public void testSetDateGiven() {
        assertEquals(dateGiven, reward.getDateGiven());
        reward.setDateGiven(LocalDate.now());
        assertEquals(LocalDate.now(), reward.getDateGiven());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDateGivenToNull() {
        reward.setDateGiven(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetDateGivenToLaterThanCurrent() {
        LocalDate badDate = LocalDate.now().plus(30, ChronoUnit.DAYS);
        reward.setDateGiven(badDate);
    }
}