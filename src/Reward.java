import ApplicationExceptions.StringTooShortException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reward {
    private int id;
    private String name;
    private String description;
    private RewardType type;
    private Date dateGiven;

    public Reward(String name, String description, RewardType type, Date dateGiven) {
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

    public void setId(int id) {
        if(id<0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public void setName(String name) throws StringTooShortException {
        if(name == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(name.length()<3) throw new StringTooShortException();
        else this.name = name;
    }

    public void setDescription(String description) throws StringTooShortException {
        if(description == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(description.length()<15) throw new StringTooShortException();
        else this.description = description;
    }

    public void setType(RewardType type) {
        if(type == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.type = type;
    }

    public void setDateGiven(Date dateGiven) {
        if(dateGiven == null) throw new IllegalArgumentException("Argument cannot be null");
        else if(dateGiven.after(new Date(System.currentTimeMillis()))) throw new IllegalArgumentException("dateGiven cannot be in future");
        else this.dateGiven = dateGiven;
    }
}