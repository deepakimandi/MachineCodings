package org.example.ATMMachine.state;

import lombok.AllArgsConstructor;
import org.example.ATMMachine.cor.CashDispenser;
import org.example.ATMMachine.cor.CashDispenserChainBuilder;
import org.example.ATMMachine.enums.ATMStatus;
import org.example.ATMMachine.model.Card;
import org.example.ATMMachine.service.ATMContext;

@AllArgsConstructor
public class DispenseCashState implements ATMState {

    private final ATMContext atmMachine;
    private final CashDispenser chain = CashDispenserChainBuilder.buildChain(); // The head of the cash dispenser chain

    @Override
    public void insertCard(Card card) {
        System.out.println("Transaction in progress. Cannot insert another card.");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Already authenticated.");
    }

    @Override
    public void selectOption(String option) {
        System.out.println("Already selected cash dispensing.");
    }

    @Override
    public void dispenseCash(int amount) {
        double atmBalance = atmMachine.getAtm().getCashAvailable();
        double accountBalance = atmMachine.getCurrentCard().getAccount().getBalance();

        if (amount > atmBalance) {
            System.out.println("ATM has insufficient cash. Cannot dispense " + amount);
            ejectCard();
            return;
        }

        if (amount > accountBalance) {
            System.out.println("Insufficient account balance. Cannot dispense " + amount);
            ejectCard();
            return;
        }

// Check if note combination is possible
        if (chain.canDispense(atmMachine.getAtm(), amount)) {
            chain.dispense(atmMachine.getAtm(), amount);

            // Deduct from ATM cash & account balance
            atmMachine.getAtm().setCashAvailable(atmBalance - amount);
            atmMachine.getCurrentCard().getAccount().setBalance(accountBalance - amount);

            atmMachine.setState(new IdleState(atmMachine)); // could use factory
            System.out.println("Cash dispensed: " + amount);
        } else {
            ejectCard();
            System.out.println("Cannot dispense requested amount with available denominations.");
        }
    }

    @Override
    public void ejectCard() {
        atmMachine.setCurrentCard(null);
        System.out.println("Card ejected.");
        atmMachine.setState(new IdleState(atmMachine)); // could use factory here
    }

    @Override
    public ATMStatus getStatus() {
        return ATMStatus.DISPENSE_CASH;
    }
}