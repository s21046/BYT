import java.util.Date;

public class Reward {
    //TODO implement id uniqueness
    private int id;
    private String name;
    private String description;
    private RewardType type;
    private Date dateGiven;

    public Reward(int id, String name, String description, RewardType type, Date dateGiven) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.dateGiven = dateGiven;
    }
}
