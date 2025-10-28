package org.example.SplitWise.service;

import lombok.AllArgsConstructor;
import org.example.SplitWise.enums.SplitType;
import org.example.SplitWise.model.Dispute;
import org.example.SplitWise.model.Expense;
import org.example.SplitWise.model.Group;
import org.example.SplitWise.model.User;
import org.example.SplitWise.repository.DisputeRepository;
import org.example.SplitWise.repository.GroupRepository;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class DisputeService {
    private final DisputeRepository disputeRepository;
    private final GroupRepository groupRepository;
    private final ExpenseService expenseService;

    public String raiseDispute(String groupId, String expenseId, User disputer, double amount) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));
        if (!group.getExpenses().containsKey(expenseId)) {
            throw new IllegalArgumentException("Expense not found: " + groupId);
        }
        Expense expense = group.getExpenses().get(expenseId);

        String id = UUID.randomUUID().toString();
        disputeRepository.save(new Dispute(id, group, expense, disputer, amount, "pending"));
        return id;
    }

    public void rejectDispute(String id) {
        Dispute dispute = disputeRepository.findById(id).get();
        dispute.setStatus("rejected");
    }

    public void acceptAndResolveDispute(String id, String expenseId, String groupId, String description, double amount, User paidBy, SplitType splitType, Map<User, Double> splits) {
        Dispute dispute = disputeRepository.findById(id).get();
        dispute.setStatus("accepted");
        Group group = dispute.getGroup();
        Expense expense = dispute.getExpense();
        if (!groupId.equals(group.getId())) {
            throw new RuntimeException("The dispute is not related to this group");
        }
        if (!expenseId.equals(expense.getId())) {
            throw new RuntimeException("The dispute is not on this expense");
        }
        expense.setSplits(splits);
        expenseService.updateExpense(expense, group, description, amount, paidBy, splitType, splits);
    }
}