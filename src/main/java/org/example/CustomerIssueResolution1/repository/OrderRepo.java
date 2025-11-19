package org.example.CustomerIssueResolution1.repository;

import org.example.CustomerIssueResolution1.model.Order;

import java.util.HashMap;

public class OrderRepo {
    private HashMap<String, Order> orders;

    public OrderRepo() {
        this.orders = new HashMap<>();
    }
}
