package org.example.ATMMachine.state;

import lombok.AllArgsConstructor;
import org.example.ATMMachine.enums.ATMStatus;
import org.example.ATMMachine.model.Card;
import org.example.ATMMachine.service.ATMContext;

@AllArgsConstructor
public class CardInsertedState implements ATMState {

    private final ATMContext atmMachine;

    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(String pin) {
        if (atmMachine.getCurrentCard().getPin().equals(pin)) {
            System.out.println("PIN correct. Authenticated.");
            atmMachine.setState(new AuthenticatedState(atmMachine));
        } else {
            System.out.println("Incorrect PIN. Try again.");
        }
    }

    @Override
    public void selectOption(String option) {
        System.out.println("Enter PIN first.");
    }

    @Override
    public void dispenseCash(int amount) {
        System.out.println("Enter PIN before dispensing cash.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected.");
        atmMachine.setCurrentCard(null);
        atmMachine.setState(new IdleState(atmMachine));
    }

    @Override
    public ATMStatus getStatus() {
        return ATMStatus.CARD_INSERTED;
    }
}
