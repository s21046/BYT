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

    //Corner cases to be added
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
     * @corner_cases setId is tested on the matter of invalid input id (negative argument)
     */

    @Test
    public void testSetId() {
        reward.setId(15);
        assertEquals(15, reward.getId());
    }

    @Test
    public void testSetName() throws StringTooShortException {
        reward.setName("FFF");
        assertEquals("FFF", reward.getName());
    }

    @Test
    public void testSetDescription() throws StringTooShortException {
        reward.setDescription("Very gooooooooooood");
        assertEquals("Very gooooooooooood", reward.getDescription());
    }

    @Test
    public void testSetType() {
        reward.setType(RewardType.TITLE);
        assertEquals(RewardType.TITLE, reward.getType());
    }

    @Test
    public void testSetDateGiven() {
        reward.setDateGiven(new Date());
        assertEquals(new Date(), reward.getDateGiven());
    }
}