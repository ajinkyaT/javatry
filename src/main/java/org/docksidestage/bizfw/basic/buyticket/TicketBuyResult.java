package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult{
    // DONE TODO start variable with small letter? by zaya 2019/10/17
    private static int displayPrice;
    private int change;
    private boolean oneDayTicket;
    private int numberOfDays = 1;

    public TicketBuyResult( int change, int displayPrice, boolean oneDayticket, int numberOfDays) {
        this.change = change;
        this.displayPrice = displayPrice;
        this.oneDayTicket = oneDayticket;

        if (!oneDayticket){
            this.numberOfDays = numberOfDays;
        }

    }


    public Ticket getTicket() {
        if (oneDayTicket){
            return new OneDayTicket();
        } else {
            return new PluralDayTicket(numberOfDays , displayPrice);
        }

    }

    public int getChange() {
        return change;
    }
}
