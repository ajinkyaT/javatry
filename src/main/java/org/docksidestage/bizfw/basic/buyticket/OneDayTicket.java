package org.docksidestage.bizfw.basic.buyticket;

public class OneDayTicket implements TicketInterface {

    private final int displayPrice = 7400;
    private boolean alreadyIn;

    public void doInPark(){
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

}
