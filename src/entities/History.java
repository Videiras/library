package entities;

import java.util.Date;

public class History {

    private final Client CLIENT;
    private final Date CHECKOUT_DATE;
    private final Date DUE_DATE;

    public History(Client CLIENT, Date CHECKOUT_DATE, Date DUE_DATE) {
        this.CLIENT = CLIENT;
        this.CHECKOUT_DATE = CHECKOUT_DATE;
        this.DUE_DATE = DUE_DATE;
    }

    public Client getCLIENT() {
        return CLIENT;
    }

    public Date getCHECKOUT_DATE() {
        return CHECKOUT_DATE;
    }

    public Date getDUE_DATE() {
        return DUE_DATE;
    }
}
