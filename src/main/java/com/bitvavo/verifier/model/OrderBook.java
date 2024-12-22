package com.bitvavo.verifier.model;

import com.bitvavo.verifier.types.OrderType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderBook {
    private final SortedSet<Order> bids = new TreeSet<>();
    private final SortedSet<Order> asks = new TreeSet<>();

    public void addOrder(Order order) {
        if (order.getOrderType().equals(OrderType.BID)) {
            bids.add(order);
        } else {
            asks.add(order);
        }
    }

    public boolean isBidsEmpty() {
        return this.bids.isEmpty();
    }

    public boolean isAsksEmpty() {
        return this.asks.isEmpty();
    }

    public Order getBestBid() {
        if (this.bids.isEmpty()) {
            return null;
        }
        return this.bids.first();
    }

    public Order getBestAsk() {
        if (this.asks.isEmpty()) {
            return null;
        }
        return this.asks.first();

    }

    public Order fetchBestBid() {
        if (this.bids.isEmpty()) {
            return null;

        }
        Order bestBid = bids.first();
        this.bids.remove(bestBid);
        return bestBid;
    }

    public Order fetchBestAsk() {
        if (this.asks.isEmpty()) {
            return null;
        }
        Order bestAsk = asks.first();
        this.asks.remove(bestAsk);
        return bestAsk;
    }

    public List<String> getOrderBook() {
        LinkedList<String> orderBook = new LinkedList<>();

        Iterator<Order> bidIterator = bids.iterator();
        Iterator<Order> askIterator = asks.iterator();

        while (bidIterator.hasNext() || askIterator.hasNext()) {
            String buyLine = String.format("%-18s", "");
            String sellLine = String.format("%-18s", "");

            if (bidIterator.hasNext()) {
                Order bidOrder = bidIterator.next();
                String bidPrice = String.format("%d", bidOrder.getPrice());
                String bidQuantity = String.format("%,d", bidOrder.getQuantity());
                buyLine = String.format("%11s%7s", bidQuantity, bidPrice);

            }
            if (askIterator.hasNext()) {
                Order askOrder = askIterator.next();
                String askPrice = String.format("%d", askOrder.getPrice());
                String askQuantity = String.format("%,d", askOrder.getQuantity());
                sellLine = String.format("%6s%12s", askPrice, askQuantity);
            }
            orderBook.add(buyLine + " | " + sellLine);
        }
        return orderBook;
    }
}