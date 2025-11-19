package org.example.ATMMachine.cor;

import org.example.ATMMachine.model.ATM;

public class OneHundredDispenser implements CashDispenser {
    private CashDispenser next;

    @Override
    public void setNextDispenser(CashDispenser next) {
        this.next = next;
    }

    @Override
    public boolean canDispense(ATM atm, int amount) {
        int count = atm.getOneHundredCount();
        int notes = Math.min(amount / 100, count);
        int remainder = amount - notes * 100;

        return remainder == 0 || (next != null && next.canDispense(atm, remainder));
    }

    @Override
    public void dispense(ATM atm, int amount) {
        int count = atm.getOneHundredCount();
        int notes = Math.min(amount / 100, count);
        atm.setOneHundredCount(count - notes);

        int remainder = amount - notes * 100;

        if (notes > 0) {
            System.out.println("Dispensed " + notes + " x 100 notes");
        }

        if (remainder > 0 && next != null) {
            next.dispense(atm, remainder);
        }
    }

}
