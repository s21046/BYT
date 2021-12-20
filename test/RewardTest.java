import ApplicationExceptions.StringTooShortException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RewardTest {
    private int id;
    private String name;
    private String description;
    private RewardType type;
    private Date dateGiven;
    private Reward reward;

    @Before
    public void setUp() {
        id = 1;
        name = "Best Reward";
        description = "Just for testing purposes";
        type = RewardType.BADGE;
        dateGiven = new Date();
        reward = new Reward(1, name, description, type, dateGiven);
    }

    /**
     * Get the given values belonging to Team object
     * @result Team object values are returned accordingly for each getter
     * @corner_cases None
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
     * Set the given values to fields belonging to Team object
     * @result Team object values are set anew and returned accordingly for each setter
     * @corner_cases
     * Methods are tested on the matter of invalid input id:
     * 	setId: negative argument
     * 	setName: null argument, name shorter than 3 characters
     * 	setDescription: null argument, description shorter than 15 characters
     * 	setType: null argument
     * 	setDateGiven: null argument, date from the future
     */

    @Test
    public void testSetId() {
        reward.setId(15);
        assertEquals(15, reward.getId());

        try {
            reward.setId(-4);
            fail();
        } catch (IllegalArgumentException e) {
            // should be thrown
        }
    }

    @Test
    public void testSetName() throws StringTooShortException {
        reward.setName("FFF");
        assertEquals("FFF", reward.getName());
        try {
            reward.setName(null);
            fail();
        } catch (IllegalArgumentException e) {
            // should be thrown
        }
        try {
            reward.setName("no");
            fail();
        } catch (StringTooShortException e) {
            // should be thrown
        }
    }

    @Test
    public void testSetDescription() throws StringTooShortException {
        reward.setDescription("Very gooooooooooood");
        assertEquals("Very gooooooooooood", reward.getDescription());
        try {
            reward.setDescription(null);
            fail();
        } catch (IllegalArgumentException e) {
            // should be thrown
        }
        try {
            reward.setDescription("gud");
            fail();
        } catch (StringTooShortException e) {
            // should be thrown
        }
    }

    @Test
    public void testSetType() {
        reward.setType(RewardType.TITLE);
        assertEquals(RewardType.TITLE, reward.getType());
        try {
            reward.setType(null);
            fail();
        } catch (IllegalArgumentException e) {
            // should be thrown
        }
    }

    @Test
    public void testSetDateGiven() {
        reward.setDateGiven(new Date());
        assertEquals(new Date(), reward.getDateGiven());
        try {
            reward.setDateGiven(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Argument cannot be null");
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 30);
        Date badDate = cal.getTime();
        try {
            reward.setDateGiven(badDate);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "dateGiven cannot be in future");
        }
    }
}