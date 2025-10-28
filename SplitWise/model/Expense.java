package org.example.SplitWise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.SplitWise.enums.SplitType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Expense {
    private final String id;
    private final String description;
    private double amount;
    private User paidBy;
    private Map<User, Double> splits = new HashMap<>();
    private final SplitType splitType;
}
