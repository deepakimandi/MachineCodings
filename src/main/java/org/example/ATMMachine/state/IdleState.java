package org.example.ATMMachine.state;

import lombok.AllArgsConstructor;
import org.example.ATMMachine.enums.ATMStatus;
import org.example.ATMMachine.model.Card;
import org.example.ATMMachine.service.ATMContext;

@AllArgsConstructor
public class IdleState implements ATMState {

    private final ATMContext atmMachine;

    @Override
    public void insertCard(Card card) {
        atmMachine.setCurrentCard(card);
        System.out.println("Card inserted.");
        atmMachine.setState(new CardInsertedState(atmMachine));
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("No card inserted.");
    }

    @Override
    public void selectOption(String option) {
        System.out.println("No card inserted.");
    }

    @Override
    public void dispenseCash(int amount) {
        System.out.println("No card inserted.");
    }

    @Override
    public void ejectCard() {
        System.out.println("No card inserted.");
    }

    @Override
    public ATMStatus getStatus() {
        return ATMStatus.IDLE;
    }
}
