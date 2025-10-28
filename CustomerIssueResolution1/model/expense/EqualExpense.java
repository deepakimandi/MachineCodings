package org.example.CustomerIssueResolution1.model.expense;

import org.example.CustomerIssueResolution1.model.User;
import org.example.CustomerIssueResolution1.model.split.EqualSplit;
import org.example.CustomerIssueResolution1.model.split.Split;

import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(double amount, User expensePaidBy, List<Split> splits, ExpenseData expenseData) {
        super(amount, expensePaidBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }
        return true;
    }

}
