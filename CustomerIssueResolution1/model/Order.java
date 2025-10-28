package org.example.CustomerIssueResolution1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private BigDecimal totalPrice;
    private List<OrderItem> orderItemList;
    private final LocalDateTime createdAt = LocalDateTime.now();

}
