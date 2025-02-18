package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class History {

    private final Client CLIENT;
    private final LocalDate CHECKOUT_DATE;
    private final LocalDate DUE_DATE;

    public History(Client CLIENT, LocalDate CHECKOUT_DATE, LocalDate DUE_DATE) {
        this.CLIENT = CLIENT;
        this.CHECKOUT_DATE = CHECKOUT_DATE;
        this.DUE_DATE = DUE_DATE;
    }

    public Client getCLIENT() {
        return CLIENT;
    }

    public LocalDate getCHECKOUT_DATE() {
        return CHECKOUT_DATE;
    }

    public LocalDate getDUE_DATE() {
        return DUE_DATE;
    }

    public String toFile(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return CLIENT.getID()
                + ","
                + dtf.format(CHECKOUT_DATE)
                + ","
                + dtf.format(DUE_DATE);
    }

}
