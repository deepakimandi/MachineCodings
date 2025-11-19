package org.example.SplitWise.service;

import lombok.AllArgsConstructor;
import org.example.SplitWise.enums.SplitType;
import org.example.SplitWise.factory.SplitStrategyFactory;
import org.example.SplitWise.model.Expense;
import org.example.SplitWise.model.Group;
import org.example.SplitWise.model.User;
import org.example.SplitWise.strategy.SplitStrategy;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class ExpenseService {
    private final BalanceSheetService balanceSheetService;

    public String addExpense(Group group, String description, double amount, User paidBy, List<User> participants, SplitType splitType, Map<User, Double> meta) {
        SplitStrategy splitStrategy = SplitStrategyFactory.getStrategy(splitType);
        Map<User, Double> splits = splitStrategy.split(amount, participants, meta);
        String id = UUID.randomUUID().toString();
        Expense expense = new Expense(id, description, amount, paidBy, splits, splitType);
        group.addExpense(expense);
        balanceSheetService.updateBalances(group, paidBy, splits);

        return id;
    }

    public void updateExpense(Expense expense, Group group, String description, double amount, User paidBy, SplitType splitType, Map<User, Double> splits) {
        group.getExpenses().put(expense.getId(), expense);
    }
}
