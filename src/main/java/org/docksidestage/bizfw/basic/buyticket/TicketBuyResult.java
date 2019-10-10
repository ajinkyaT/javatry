package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult{
    private static int DisplayPrice;
    private int change;

    public TicketBuyResult( int change, int DisplayPrice) {
        this.change = change;
        this.DisplayPrice = DisplayPrice;
                    }


    public Ticket getTicket() {
        return new Ticket(DisplayPrice);
    }

    public int getChange() {
        return change;
    }
}
