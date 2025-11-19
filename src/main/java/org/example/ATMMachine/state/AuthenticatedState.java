package org.example.ATMMachine.state;

import lombok.AllArgsConstructor;
import org.example.ATMMachine.enums.ATMStatus;
import org.example.ATMMachine.factory.ATMStateFactory;
import org.example.ATMMachine.model.Card;
import org.example.ATMMachine.service.ATMContext;

@AllArgsConstructor
public class AuthenticatedState implements ATMState {

    private final ATMContext atmMachine;

    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Already authenticated.");
    }

    @Override
    public void selectOption(String option) {
        // Can add options like deposit, check balance, etc.
        System.out.println("Option selected: Withdrawal.");
        atmMachine.setState(ATMStateFactory.getInstance().getState(ATMStatus.DISPENSE_CASH, atmMachine));
    }

    @Override
    public void dispenseCash(int amount) {
        System.out.println("Select an option first.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected.");
        atmMachine.setCurrentCard(null);
        atmMachine.setState(new IdleState(atmMachine));
    }

    @Override
    public ATMStatus getStatus() {
        return ATMStatus.AUTHENTICATED;
    }
}
