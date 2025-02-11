package entities;

import entities.enums.Disponibilty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Book {

    private final String ID;
    private final String TITLE;
    private final Author AUTHOR;
    private Disponibilty disponibilty;
    private final LocalDate REGISTRATION_DATE;
    private LocalDate updateDate;

    List<History> loanHistory = new ArrayList<>();

    public Book(String title, Author author) {
        this.ID = UUID.randomUUID().toString();
        this.TITLE = title;
        this.AUTHOR = author;
        this.disponibilty = Disponibilty.available;
        this.REGISTRATION_DATE = LocalDate.now();
        this.updateDate = REGISTRATION_DATE;
    }

    public String getID() {
        return ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public Author getAUTHOR() {
        return AUTHOR;
    }

    public Disponibilty getDisponibilty() {
        return disponibilty;
    }

    public void setDisponibilty(Disponibilty disponibilty) {
        this.disponibilty = disponibilty;
    }

    public LocalDate getREGISTRATION_DATE() {
        return REGISTRATION_DATE;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public List<History> getLoanHistory() {
        return loanHistory;
    }
}
