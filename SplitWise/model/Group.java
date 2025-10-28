package org.example.SplitWise.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Group {
    private final String id;
    private final String name;
    private final List<User> members = new ArrayList<>();
    private final Map<String, Expense> expenses = new HashMap<>();
    private final Map<User, BalanceSheet> balanceSheets = new HashMap<>();

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void addExpense(Expense expense) {
        expenses.put(expense.getId(), expense);
    }

    public BalanceSheet getBalanceSheet(User user) {
        if (!balanceSheets.containsKey(user)) {
            balanceSheets.put(user, new BalanceSheet());
        }
        return balanceSheets.get(user);
    }
}
