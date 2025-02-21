package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class History {

    private final Client CLIENT;
    private final LocalDate CHECKOUT_DATE;
    private LocalDate dueDate;

    public History(Client CLIENT, LocalDate CHECKOUT_DATE, LocalDate dueDate) {
        this.CLIENT = CLIENT;
        this.CHECKOUT_DATE = CHECKOUT_DATE;
        this.dueDate = dueDate;
    }

    public Client getCLIENT() {
        return CLIENT;
    }

    public LocalDate getCHECKOUT_DATE() {
        return CHECKOUT_DATE;
    }

    public LocalDate getDUE_DATE() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String toFile(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return CLIENT.getID()
                + ","
                + CHECKOUT_DATE
                + ","
                + dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(CLIENT, history.CLIENT) && Objects.equals(CHECKOUT_DATE, history.CHECKOUT_DATE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CLIENT, CHECKOUT_DATE);
    }
}
