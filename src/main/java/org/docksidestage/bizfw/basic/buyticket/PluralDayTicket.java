package org.docksidestage.bizfw.basic.buyticket;

public class PluralDayTicket implements TicketInterface {

    private int displayPrice;
    private int NoOfDays;
    private int counterDays;
    private boolean alreadyIn;

    public PluralDayTicket(int numberOfDays ,int displayPrice) {
        this.NoOfDays = numberOfDays;
        this.counterDays = numberOfDays;
        this.displayPrice = displayPrice;
    }

    public void doInPark(){

        if (counterDays < 1) {
            throw new IllegalStateException("Sorry number of days with this ticket has expired");
        }
        alreadyIn = true;
        --counterDays;
    };

    public int getDisplayPrice() {
        return displayPrice;
    };

    public String getTicketType() {
        return NoOfDays + " Days ticket.";
    }

    public int counterDays() {
        return counterDays;
    };
    public boolean isAlreadyIn() {
        return alreadyIn;
    }
}
