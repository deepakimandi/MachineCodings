package org.example.ATMMachine.service;

import lombok.Getter;
import lombok.Setter;
import org.example.ATMMachine.factory.ATMStateFactory;
import org.example.ATMMachine.model.ATM;
import org.example.ATMMachine.model.Card;
import org.example.ATMMachine.repository.ATMRepository;
import org.example.ATMMachine.state.ATMState;

@Getter
public class ATMContext {

    private final ATM atm;
    private ATMState state;
    private final ATMRepository atmRepository;

    @Setter
    private Card currentCard;

    public ATMContext(String atmId, ATMRepository atmRepository) {
        this.atmRepository = atmRepository;
        this.atm = atmRepository.getById(atmId)
                .orElseThrow(() -> new RuntimeException("ATM not found"));

        this.state = ATMStateFactory.getInstance().getState(atm.getStatus(), this);
    }

    public void insertCard(Card card) {
        state.insertCard(card);
    }

    public void enterPin(String pin) {
        state.enterPin(pin);
    }

    public void selectOption(String option) {
        state.selectOption(option);
    }

    public void dispenseCash(int amount) {
        state.dispenseCash(amount);
    }

    public void ejectCard() {
        state.ejectCard();
    }

    public void setState(ATMState state) {
        this.state = state;
    }
}
