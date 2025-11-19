package org.example.ATMMachine.model;

import lombok.Getter;
import lombok.Setter;
import org.example.ATMMachine.enums.ATMStatus;

@Getter
public class ATM {

    private final String id;

    @Setter
    private ATMStatus status;

    @Setter
    private double cashAvailable;

    @Setter
    private int twoThousandCount;

    @Setter
    private int fiveHundredCount;

    @Setter
    private int oneHundredCount;

//    private ATMInventory inventory;

    public ATM(String id, int twoThousandCount, int fiveHundredCount, int oneHundredCount, double cashAvailable, ATMStatus status) {
        this.id = id;
        this.twoThousandCount = twoThousandCount;
        this.fiveHundredCount = fiveHundredCount;
        this.oneHundredCount = oneHundredCount;
        this.cashAvailable = cashAvailable;
        this.status = status;
    }


}
