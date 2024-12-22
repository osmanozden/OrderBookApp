package com.bitvavo.verifier.model;

import com.bitvavo.verifier.types.OrderType;
import java.util.Objects;

public class Order implements Comparable<Order> {
    private final String id;
    private final OrderType orderType;
    private final int price;
    private final int quantity;


    public Order(String id, OrderType orderType, int price, int quantity) {
        this.id = id;
        this.orderType = orderType;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderType=" + orderType +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return price == order.price && quantity == order.quantity && id.equals(order.id) && orderType == order.orderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderType, price, quantity);
    }

    @Override
    public int compareTo(Order o) {
        if (!this.getOrderType().equals(o.getOrderType())) {
            throw new IllegalArgumentException("Cannot compare orders with different sides");
        }
        int priceComparison = Integer.compare(o.getPrice(), this.getPrice());
        if (priceComparison != 0) {
            return this.getOrderType().equals(OrderType.BID) ? priceComparison : -priceComparison;
        } else {
            return this.getId().compareTo(o.getId());
        }
    }
}
