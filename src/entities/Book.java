package entities;

import entities.enums.Disponibilty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Book {

    private final Integer ID;
    private final String TITLE;
    private final Author AUTHOR;
    private Disponibilty disponibilty;
    private final LocalDate REGISTRATION_DATE;
    private LocalDate updateDate;

    List<History> loanHistory = new ArrayList<>();

    public Book(String title, Author author) {
        this.ID = UUID.randomUUID().hashCode();
        this.TITLE = title;
        this.AUTHOR = author;
        this.disponibilty = Disponibilty.available;
        this.REGISTRATION_DATE = LocalDate.now();
        this.updateDate = REGISTRATION_DATE;
    }

    public Integer getID() {
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
        this.updateDate = LocalDate.now();
    }

    public String getREGISTRATION_DATE() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(REGISTRATION_DATE);
    }

    public String getUpdateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(updateDate);
    }

    public List<History> getLoanHistory() {
        return loanHistory;
    }

    @Override
    public String toString(){
        return TITLE
            + " | "
            + "Autor: "
            + AUTHOR
            + " | Data de registro: "
            + getREGISTRATION_DATE()
            + " | Data de atualização: "
            + getUpdateDate();
    }
}
