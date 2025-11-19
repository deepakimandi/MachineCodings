package org.example.ATMMachine.model;

import org.example.ATMMachine.enums.Denomination;

import java.util.HashMap;
import java.util.Map;

public class ATMInventory {
    private Map<Denomination, Integer> cashInventory;

    public ATMInventory() {
        cashInventory = new HashMap<>();
        initializeInventory();
    }

    public ATMInventory(Map<Denomination, Integer> cashInventory) {
        this.cashInventory = cashInventory;
    }

    private void initializeInventory() {
        cashInventory.put(Denomination.BILL_2000, 10);
        cashInventory.put(Denomination.BILL_500, 10);
        cashInventory.put(Denomination.BILL_100, 20);
    }

    public int getCount(Denomination denomination) {
        return cashInventory.get(denomination);
    }
}
