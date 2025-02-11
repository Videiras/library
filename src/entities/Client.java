package entities;

import java.util.Date;

public class Client {

    private final Integer ID;
    private final String NAME;
    private final Date BORN_DATE;
    private String email;

    public Client(Integer id, String name, Date bornDate, String email) {
        this.ID = id;
        this.NAME = name;
        this.BORN_DATE = bornDate;
        this.email = email;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return NAME;
    }

    public Date getBornDate() {
        return BORN_DATE;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
