package org.example.ATMMachine.factory;

import org.example.ATMMachine.enums.ATMStatus;
import org.example.ATMMachine.service.ATMContext;
import org.example.ATMMachine.state.*;

public class ATMStateFactory {
    private static ATMStateFactory instance = null;

    private ATMStateFactory() {}

    public static ATMStateFactory getInstance() {
        if (instance == null) {
            instance = new ATMStateFactory();
        }
        return instance;
    }

    public ATMState getState(ATMStatus atmStatus, ATMContext atmMachine) {
        return switch (atmStatus) {
            case IDLE -> new IdleState(atmMachine);
            case CARD_INSERTED -> new CardInsertedState(atmMachine);
            case AUTHENTICATED -> new AuthenticatedState(atmMachine);
            case DISPENSE_CASH -> new DispenseCashState(atmMachine);
        };
    }
}
