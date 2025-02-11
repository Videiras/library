package entities;

import java.time.LocalDate;
import java.util.UUID;

public class Author {

    private final String ID;
    private final String NAME;
    private final LocalDate BORN_DATE;

    public Author(String name, LocalDate bornDate) {
        this.ID = UUID.randomUUID().toString();
        this.NAME = name;
        this.BORN_DATE = bornDate;
    }

    public String getId() {
        return ID;
    }

    public LocalDate getBORN_DATE() {
        return BORN_DATE;
    }

    public String getNAME() {
        return NAME;
    }
}
