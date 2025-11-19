package org.example.ATMMachine.cor;

public class CashDispenserChainBuilder {

    public static CashDispenser buildChain() {
        CashDispenser d1 = new TwoThousandDispenser();
        CashDispenser d2 = new FiveHundredDispenser();
        CashDispenser d3 = new OneHundredDispenser();

//        CashDispenser d1 = new DenominationDispenser(2000, atm::getTwoThousandCount, atm::setTwoThousandCount);
//        CashDispenser d2 = new DenominationDispenser(500, atm::getFiveHundredCount, atm::setFiveHundredCount);
//        CashDispenser d3 = new DenominationDispenser(100, atm::getOneHundredCount, atm::setOneHundredCount);


        d1.setNextDispenser(d2);
        d2.setNextDispenser(d3);

        return d1;
    }
}
