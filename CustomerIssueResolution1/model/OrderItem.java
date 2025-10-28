package org.example.CustomerIssueResolution1.model;

import org.example.CustomerIssueResolution1.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderItem {
    private Long id;
    private int quantity;
    private BigDecimal price;
    private OrderStatus status;
    private User user;
    private Product product;
    private Order order;
    private final LocalDateTime createdAt = LocalDateTime.now();

}
