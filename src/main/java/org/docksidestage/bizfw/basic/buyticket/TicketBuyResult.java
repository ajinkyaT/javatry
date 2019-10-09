package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult{
    private int handedMoney = 0;
    private static final int TWO_DAY_PRICE = 13200;
    private int change;

    public TicketBuyResult(int money, int change) {
        this.handedMoney = money;
        this.change = change;
                    }


    public Ticket getTicket() {
        return new Ticket(TWO_DAY_PRICE);
    }

    public int getChange() {
        return change;
    }
}
