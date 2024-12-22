package com.bitvavo.verifier.service;

import com.bitvavo.verifier.model.Order;
import com.bitvavo.verifier.model.OrderBook;
import com.bitvavo.verifier.model.Trade;
import com.bitvavo.verifier.types.OrderType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ExchangeService {

    private final OrderBook orderBook;
    private final List<Trade> trades = new LinkedList<>();

    public ExchangeService() {
        this.orderBook = new OrderBook();
    }

    public void match(Order order) {
        if (order.getOrderType().equals(OrderType.BID)) {
            matchAgainstSellOrders(order);
        } else {
            matchAgainstBuyOrders(order);
        }
    }

    private void matchAgainstSellOrders(Order order) {
        while (!this.orderBook.isAsksEmpty() && this.orderBook.getBestAsk().getPrice() <= order.getPrice() && order.getQuantity() > 0) {
            Order ask = this.orderBook.fetchBestAsk();
            int tradeQuantity = Math.min(order.getQuantity(), ask.getQuantity());
            addTrade(order, ask, tradeQuantity);
            order = new Order(order.getId(), order.getOrderType(), order.getPrice(), order.getQuantity() - tradeQuantity);
            ask = new Order(ask.getId(), ask.getOrderType(), ask.getPrice(), ask.getQuantity() - tradeQuantity);
            if (ask.getQuantity() > 0) {
                this.orderBook.addOrder(ask);
            }
        }
        if (order.getQuantity() > 0) {
            this.orderBook.addOrder(order);
        }

    }

    private void matchAgainstBuyOrders(Order order) {
        while (!this.orderBook.isBidsEmpty() &&
                this.orderBook.getBestBid().getPrice() >= order.getPrice() &&
                order.getQuantity() > 0) {
            Order bid = this.orderBook.fetchBestBid();
            int tradeQuantity = Math.min(order.getQuantity(), bid.getQuantity());
            addTrade(bid, order, tradeQuantity);
            order = new Order(order.getId(), order.getOrderType(), order.getPrice(), order.getQuantity() - tradeQuantity);
            bid = new Order(bid.getId(), bid.getOrderType(), bid.getPrice(), bid.getQuantity() - tradeQuantity);
            if (bid.getQuantity() > 0) {
                this.orderBook.addOrder(bid);
            }
        }
        if (order.getQuantity() > 0) {
            this.orderBook.addOrder(order);
        }
    }


    public void getOrderBook(List<String> orderBook) {
        for (String order : orderBook) {
            System.out.println(order);
        }
    }

    public List<String> getTrades() {
        LinkedList<String> tradeList = new LinkedList<>();
        for (Trade trade : this.trades) {
            tradeList.add(String.format("trade %s,%s,%d,%d", trade.getBid().getId(), trade.getAsk().getId(), trade.getTradePrice(), trade.getTradeQuantity()));
        }
        return Collections.unmodifiableList(tradeList);
    }

    public void addTrade(Order bid, Order ask, int tradeQuantity) {
        this.trades.add(new Trade(bid, ask, tradeQuantity, ask.getPrice()));
    }
}