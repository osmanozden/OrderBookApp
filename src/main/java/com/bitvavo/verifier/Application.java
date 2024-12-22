package com.bitvavo.verifier;

import com.bitvavo.verifier.model.Order;
import com.bitvavo.verifier.model.OrderBook;
import com.bitvavo.verifier.service.ExchangeService;
import com.bitvavo.verifier.types.OrderType;

import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderBook orderBook = new OrderBook();
        ExchangeService exchange = new ExchangeService();
        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Invalid input format");
                }
                String orderId = parts[0];
                OrderType side = parts[1].charAt(0) == 'B' ? OrderType.BID : OrderType.ASK;
                int price = Integer.parseInt(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                Order order = new Order(orderId, side, price, quantity);
                orderBook.addOrder(order);
                exchange.match(order);
            }
            exchange.getOrderBook(orderBook.getOrderBook());
            exchange.getTrades().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
        sc.close();
    }
}
