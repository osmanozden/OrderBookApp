package com.bitvavo.verifier.model;

public class Trade {
    private final Order bid;
    private final Order ask;
    private final int tradeQuantity;
    private final int tradePrice;

    public Order getBid() {
        return bid;
    }

    public Order getAsk() {
        return ask;
    }

    public int getTradeQuantity() {
        return tradeQuantity;
    }

    public int getTradePrice() {
        return tradePrice;
    }

    public Trade(Order bid, Order ask, int tradeQuantity, int tradePrice) {
        this.bid = bid;
        this.ask = ask;
        this.tradeQuantity = tradeQuantity;
        this.tradePrice = tradePrice;
    }


}