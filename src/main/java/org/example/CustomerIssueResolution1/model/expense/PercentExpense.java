package org.example.CustomerIssueResolution1.model.expense;

import org.example.CustomerIssueResolution1.model.User;
import org.example.CustomerIssueResolution1.model.split.Split;

import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(double amount, User expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        super(amount, expensePaidBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        return false;
    }
}
