package org.example.SplitWise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Dispute {
    private final String id;
    private final Group group;
    private final Expense expense;
    private final User disputer;
    private final double disputeAmount;
    private String status;
}
