package entities;

import java.time.LocalDate;

public class Loan {

    private final Book BOOK;
    private final Client CLIENT;
    private final LocalDate CHECKOUT_DATE;
    private LocalDate dueDate;

    public Loan(Book book, Client client, LocalDate checkoutDate) {
        this.BOOK = book;
        this.CLIENT = client;
        this.CHECKOUT_DATE = LocalDate.now();
    }

    public Book getBook() {
        return BOOK;
    }

    public Client getCLIENT() {
        return CLIENT;
    }

    public LocalDate getCHECKOUT_DATE() {
        return CHECKOUT_DATE;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
