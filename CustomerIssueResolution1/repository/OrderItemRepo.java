package org.example.CustomerIssueResolution1.repository;

import org.example.CustomerIssueResolution1.model.OrderItem;

import java.util.HashMap;

public class OrderItemRepo {
    private HashMap<String, OrderItem> orderItems;

    public OrderItemRepo() {
        this.orderItems = new HashMap<>();
    }
}
