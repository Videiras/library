package entities;

import entities.enums.IsGuest;
import entities.enums.Pending;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Client {

    private final Integer ID;
    private final String NAME;
    private final LocalDate BORN_DATE;
    private String email;
    private Pending pending;
    private IsGuest isGuest;
    private Integer borrowedBook;

    public Client(String name, LocalDate bornDate, String email, IsGuest isGuest) {
        this.ID = UUID.randomUUID().hashCode();
        this.NAME = name;
        this.BORN_DATE = bornDate;
        this.email = email;
        this.isGuest = isGuest;
        this.pending = Pending.no;
        this.borrowedBook = 0;
    }

    public Client(Integer ID, String NAME, LocalDate BORN_DATE, String email, Pending pending, Integer borrowedBook, IsGuest isGuest) {
        this.ID = ID;
        this.NAME = NAME;
        this.BORN_DATE = BORN_DATE;
        this.email = email;
        this.pending = pending;
        this.isGuest = isGuest;
        this.borrowedBook = borrowedBook;
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

    public Pending getPending() {
        return pending;
    }

    public void setPending(Pending pending) {
        this.pending = pending;
    }

    public IsGuest getIsGuest() {
        return isGuest;
    }

    public void setIsGuest(IsGuest isGuest) {
        this.isGuest = isGuest;
    }

    public Integer getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Integer borrowedBook) {
        this.borrowedBook = borrowedBook;
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

    public String toFile() {
        return ID
            + ","
            + NAME
            + ","
            + BORN_DATE
            + ","
            + email
            + ","
            + pending
            + ","
            + isGuest;
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
