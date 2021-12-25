import ApplicationExceptions.StringTooShortException;

import java.time.LocalDate;

public class Reward {
    private int id;
    private String name;
    private String description;
    private RewardType type;
    private LocalDate dateGiven;

    public Reward(String name, String description, RewardType type, LocalDate dateGiven) throws StringTooShortException {
        if (description == null || name == null || type == null || dateGiven ==  null)
        { throw new IllegalArgumentException("Argument cannot be null"); }
        if (description.length() < 15 || name.length() < 3) { throw new StringTooShortException(); }
        if (dateGiven.isAfter(LocalDate.now())) throw new IllegalArgumentException("dateGiven cannot be in future");

        this.name = name;
        this.description = description;
        this.type = type;
        this.dateGiven = dateGiven;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id cannot be a negative integer.");
        else this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws StringTooShortException {
        if (name == null) throw new IllegalArgumentException("Argument cannot be null");
        if (name.length() < 3) throw new StringTooShortException();
        else this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws StringTooShortException {
        if (description == null) throw new IllegalArgumentException("Argument cannot be null");
        if (description.length() < 15) throw new StringTooShortException();
        else this.description = description;
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        if (type == null) throw new IllegalArgumentException("Argument cannot be null");
        else this.type = type;
    }

    public LocalDate getDateGiven() {
        return dateGiven;
    }

    public void setDateGiven(LocalDate dateGiven) {
        if (dateGiven == null) throw new IllegalArgumentException("Argument cannot be null");
        if (dateGiven.isAfter(LocalDate.now())) throw new IllegalArgumentException("dateGiven cannot be in future");
        else this.dateGiven = dateGiven;
    }
}