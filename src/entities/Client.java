package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Client {

    private final Integer ID;
    private final String NAME;
    private final LocalDate BORN_DATE;
    private String email;

    public Client(String name, LocalDate bornDate, String email) {
        this.ID = UUID.randomUUID().hashCode();
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

    public LocalDate getBornDate() {
        return BORN_DATE;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return NAME
            + " | "
            + "E-mail: "
            + email
            + " | Data de nascimento: "
            + dtf.format(BORN_DATE);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
