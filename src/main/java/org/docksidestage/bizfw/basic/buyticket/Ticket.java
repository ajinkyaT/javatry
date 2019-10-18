package org.docksidestage.bizfw.basic.buyticket;

// DONE TODO fix name of interface, "interface TicketInterface" something is unnecessary by zaya 2019/10/17
public interface Ticket {
    public void doInPark();
    public int getDisplayPrice();
    String getTicketType();
    public int counterDays();
    boolean isAlreadyIn();

}
