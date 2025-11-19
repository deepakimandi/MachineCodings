package org.example.SplitWise.service;

import org.example.SplitWise.model.Group;
import org.example.SplitWise.model.Split;
import org.example.SplitWise.model.User;

import java.util.Map;

public class BalanceSheetService {
    public void updateBalances(Group group, User paidBy, Map<User, Double> splits) {
        double totalAmount = splits.values().stream().mapToDouble(Double::doubleValue).sum();
//        if (group.getBalanceSheet(paidBy).getBalances().containsKey())
        group.getBalanceSheet(paidBy).addTotalPaid(totalAmount);

        for (User user : splits.keySet()) {
            double amount = splits.get(user);
            group.getBalanceSheet(user).addTotalExpense(amount);
            if (!user.equals(paidBy)) {
                group.getBalanceSheet(user).addBalance(paidBy, -amount);
                group.getBalanceSheet(paidBy).addBalance(user, amount);
            }
        }
    }
}
