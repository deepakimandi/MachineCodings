package org.example.ATMMachine;

import org.example.ATMMachine.cor.CashDispenser;
import org.example.ATMMachine.cor.CashDispenserChainBuilder;
import org.example.ATMMachine.enums.ATMStatus;
import org.example.ATMMachine.model.ATM;
import org.example.ATMMachine.model.Account;
import org.example.ATMMachine.model.Card;
import org.example.ATMMachine.repository.ATMRepository;
import org.example.ATMMachine.service.ATMContext;

public class Main {

    public static void main(String[] args) {
        // Create a card with account
        Account account = new Account("ACC123", 5000);
        Card card = new Card("CARD123", "1234", account);

        // Create ATMs
        ATM atm1 = new ATM("ATM1", 5, 10, 20, 100000, ATMStatus.IDLE);
        ATM atm2 = new ATM("ATM2", 0, 5, 10, 50000, ATMStatus.IDLE);

        // Create ATM repository and save ATMs
        ATMRepository atmRepository = new ATMRepository();
        atmRepository.save(atm1);
        atmRepository.save(atm2);

        // Build cash dispenser chain for ATM2
        CashDispenser chain = CashDispenserChainBuilder.buildChain();

        // Create ATM Machine
        ATMContext atmMachine2 = new ATMContext("ATM2", atmRepository);

        // Inject chain into DispenseCashState when needed (simplified for test)
        atmMachine2.insertCard(card);
        atmMachine2.enterPin("1234");
        atmMachine2.selectOption("WITHDRAW");
        atmMachine2.dispenseCash(1000);
    }
}
