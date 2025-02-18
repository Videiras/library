package entities;

import entities.enums.Disponibility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Book {

    private final Integer ID;
    private final String TITLE;
    private final Author AUTHOR;
    private Disponibility disponibility;
    private final LocalDate REGISTRATION_DATE;
    private LocalDate updateDate;

    List<History> loanHistory = new ArrayList<>();

    public Book(String title, Author author) {
        this.ID = UUID.randomUUID().hashCode();
        this.TITLE = title;
        this.AUTHOR = author;
        this.disponibility = Disponibility.available;
        this.REGISTRATION_DATE = LocalDate.now();
        this.updateDate = REGISTRATION_DATE;
    }

    public Book(Integer ID, String TITLE, Author AUTHOR, Disponibility disponibility, LocalDate REGISTRATION_DATE, LocalDate updateDate, List<History> loanHistory) {
        this.ID = ID;
        this.TITLE = TITLE;
        this.AUTHOR = AUTHOR;
        this.disponibility = disponibility;
        this.REGISTRATION_DATE = REGISTRATION_DATE;
        this.updateDate = updateDate;
        this.loanHistory = loanHistory;
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

    public Disponibility getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(Disponibility disponibility) {
        this.disponibility = disponibility;
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

    public void setLoanHistory(List<History> loanHistory) {
        this.loanHistory = loanHistory;
    }

    @Override
    public String toString(){
        return TITLE
            + " | Autor: "
            + AUTHOR
            + " | Data de registro: "
            + getREGISTRATION_DATE()
            + " | Data de atualização: "
            + getUpdateDate()
            + " | ID: "
            + ID;
    }

    public String toFile() {
        return ID
                + ","
                + TITLE
                + ","
                + AUTHOR.getId()
                + ","
                + disponibility
                + ","
                + REGISTRATION_DATE
                + ","
                + updateDate;
    }

}
