package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult{
    // TODO start variable with small letter? by zaya 2019/10/17
    private static int DisplayPrice;
    private int change;
    private boolean oneDayTicket;
    private int numberOfDays = 1;

    public TicketBuyResult( int change, int DisplayPrice, boolean oneDayticket, int numberOfDays) {
        this.change = change;
        this.DisplayPrice = DisplayPrice;
        this.oneDayTicket = oneDayticket;

        if (!oneDayticket){
            this.numberOfDays = numberOfDays;
        }

    }


    public TicketInterface getTicket() {
        if (oneDayTicket){
            return new OneDayTicket();
        } else {
            return new PluralDayTicket(numberOfDays , DisplayPrice);
        }

    }

    public int getChange() {
        return change;
    }
}
