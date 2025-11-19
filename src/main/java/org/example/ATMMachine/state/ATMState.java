package org.example.ATMMachine.state;

import org.example.ATMMachine.enums.ATMStatus;
import org.example.ATMMachine.model.Card;

public interface ATMState {

    void insertCard(Card card);

    void enterPin(String pin);

    void selectOption(String option);

    void dispenseCash(int amount);

    void ejectCard();

    ATMStatus getStatus();
}
