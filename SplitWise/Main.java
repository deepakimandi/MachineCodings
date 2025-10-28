package org.example.SplitWise;

import org.example.SplitWise.enums.SplitType;
import org.example.SplitWise.model.Group;
import org.example.SplitWise.model.Split;
import org.example.SplitWise.model.User;
import org.example.SplitWise.repository.InMemoryGroupRepository;
import org.example.SplitWise.service.BalanceSheetService;
import org.example.SplitWise.service.DebtSimplificationService;
import org.example.SplitWise.service.ExpenseService;
import org.example.SplitWise.service.GroupService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User shubh = new User("u1", "Shubh");
        User bob = new User("u1", "Bob");
        User tom = new User("u1", "Tom");
        User jake = new User("u1", "Jake");

        InMemoryGroupRepository repo = new InMemoryGroupRepository();
        BalanceSheetService balanceSheetService = new BalanceSheetService();
        ExpenseService expenseService = new ExpenseService(balanceSheetService);
        DebtSimplificationService simplificationService = new DebtSimplificationService();

        GroupService groupService = new GroupService(repo, expenseService, simplificationService);

        String goaGroupId = groupService.createGroup("Goa Trip", List.of(shubh, bob, tom));
        String miscGroupId = groupService.createGroup("Non-group expenses", List.of(shubh, jake));

        groupService.addExpense(goaGroupId, "Lunch Day-1",  100, shubh, List.of(shubh, bob), SplitType.EQUAL, null);
        groupService.addExpense(goaGroupId, "Lunch Day-2",  90, shubh, List.of(shubh, tom, bob), SplitType.EQUAL, null);

        groupService.simplifyDebts(goaGroupId);
        groupService.printBalances(goaGroupId);
    }
}