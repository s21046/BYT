import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reward {
    private static int uniqueId = 0;
    private int id;
    private String name;
    private String description;
    private RewardType type;
    private Date dateGiven;

    public Reward(String name, String description, RewardType type, Date dateGiven) {
        this.id = uniqueId++;
        this.name = name;
        this.description = description;
        this.type = type;
        this.dateGiven = dateGiven;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RewardType getType() {
        return type;
    }

    public Date getDateGiven() {
        return dateGiven;
    }
}
