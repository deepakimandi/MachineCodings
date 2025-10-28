package org.example.SplitWise.service;

import lombok.AllArgsConstructor;
import org.example.SplitWise.enums.SplitType;
import org.example.SplitWise.model.BalanceSheet;
import org.example.SplitWise.model.Expense;
import org.example.SplitWise.model.Group;
import org.example.SplitWise.model.User;
import org.example.SplitWise.repository.GroupRepository;

import java.util.*;

@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final ExpenseService expenseService;
    private final DebtSimplificationService simplifier;

    public String createGroup(String name, List<User> members) {
        String id = UUID.randomUUID().toString();
        Group group = new Group(id, name);
        members.forEach(group::addMember);
        groupRepository.save(group);
        return id;
    }

    public void addMember(String groupId, User user) {
        groupRepository.findById(groupId).get().addMember(user);
    }

    public String addExpense(String groupId, String description, double amount, User paidBy, List<User> participants, SplitType splitType, Map<User, Double> splits) {
        return expenseService.addExpense(get(groupId), description, amount, paidBy, participants, splitType, splits);
    }

    public void updateExpense(String expenseId, String groupId, String description, double amount, User paidBy, SplitType splitType, Map<User, Double> splits) {
        expenseService.updateExpense(get(groupId, expenseId), get(groupId), description, amount, paidBy, splitType, splits);
    }

    private Group get(String id) {
        return groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group not found: " + id));
    }

    private Expense get(String groupId, String expenseId) {
        return get(groupId).getExpenses().get(expenseId);
    }

    public void simplifyDebts(String groupId) {
        simplifier.simplifyDebts(get(groupId));
    }

    public void printBalances(String groupId) {
        Group group = get(groupId);
        System.out.println("=== Balances for group: " + group.getName() + " ===");

        Set<String> printed = new HashSet<>();

        for (User user : group.getMembers()) {
            BalanceSheet sheet = group.getBalanceSheet(user);
            if (sheet == null) continue;

            for (Map.Entry<User, Double> entry : sheet.getBalances().entrySet()) {
                User other = entry.getKey();
                double amount = entry.getValue();

                // Print each pair only once (avoid duplicates like A→B and B→A)
                String key = user.getId().compareTo(other.getId()) < 0
                        ? user.getId() + "-" + other.getId()
                        : other.getId() + "-" + user.getId();

                if (printed.contains(key)) continue;
                printed.add(key);

                if (Math.abs(amount) < 1e-6) continue;

                if (amount < 0) {
                    // user owes other
                    System.out.printf("%s owes %s: ₹%.2f%n",
                            user.getName(), other.getName(), Math.abs(amount));
                } else {
                    // other owes user
                    System.out.printf("%s owes %s: ₹%.2f%n",
                            other.getName(), user.getName(), amount);
                }
            }
        }

        if (printed.isEmpty()) {
            System.out.println("All settled up! 🎉");
        }
        System.out.println("========================================\n");
    }

}
