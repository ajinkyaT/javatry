/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400;
    private static final int TWO_DAY_PRICE = 13200;// when 2019/06/15
    private static final int FOUR_DAY_PRICE = 22400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds;
    private int change = 0;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public OneDayTicket buyOneDayPassport(int handedMoney) {
        change = buyTicket(ONE_DAY_PRICE, handedMoney);
        return new OneDayTicket();
    }

    public TicketBuyResult buyAnyDayPassport(int displayPrice, int handedMoney, int numberOfDays) {
        change = buyTicket(displayPrice, handedMoney);
        return new TicketBuyResult(change, displayPrice, false, numberOfDays);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        // DONE TODO you have nice method there named buyAnyDayPassport()
        //  so you can just
        //  return buyAnyDayPassport(TWO_DAY_PRICE, handedMoney, 2); by zaya 2019/10/17
        change = buyTicket(TWO_DAY_PRICE, handedMoney);
        return buyAnyDayPassport(TWO_DAY_PRICE, handedMoney, 2);
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        change = buyTicket(FOUR_DAY_PRICE, handedMoney);
        return new TicketBuyResult(change, FOUR_DAY_PRICE, false, 4);
    }

    // DONE TODO bit long method here, you can refactor by creating new handlingMoney() method? or smth like that if you want to by zaya 2019/10/17
    private int buyTicket(int displayPrice, int handedMoney) {
        // DONE TODO redeclared, first one in L36 by zaya 2019/10/17
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < displayPrice) {
            throw new TicketShortMoneyException("Short money: " + (displayPrice - handedMoney));
        }
        --quantity;
        change = handlingMoney(displayPrice, handedMoney);
        return change;

    }

    private int handlingMoney(int displayPrice, int handedMoney ){
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + displayPrice;
        } else {
            salesProceeds = displayPrice;
        }

        if (handedMoney > displayPrice) {
            change = handedMoney - displayPrice;
        }
        return change;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
