package entities;

import java.time.LocalDate;
import java.util.UUID;

public class Author {

    private final Integer ID;
    private final String NAME;
    private final LocalDate BORN_DATE;

    public Author(String name, LocalDate bornDate) {
        this.ID = UUID.randomUUID().hashCode();
        this.NAME = name;
        this.BORN_DATE = bornDate;
    }

    public Author(Integer ID, String NAME, LocalDate BORN_DATE) {
        this.ID = ID;
        this.NAME = NAME;
        this.BORN_DATE = BORN_DATE;
    }

    public Integer getId() {
        return ID;
    }

    public LocalDate getBORN_DATE() {
        return BORN_DATE;
    }

    public String getNAME() {
        return NAME;
    }

    @Override
    public String toString(){
        return NAME
            + " | "
            + "ID do autor: "
            + ID;
    }

    public String toFile() {
        return ID
                + ","
                + NAME
                + ","
                + BORN_DATE;
    }
}
