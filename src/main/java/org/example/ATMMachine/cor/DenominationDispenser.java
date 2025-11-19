package org.example.ATMMachine.cor;

import org.example.ATMMachine.model.ATM;

import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class DenominationDispenser implements CashDispenser {

    private final int denomination;
    private final IntSupplier getCount;
    private final IntConsumer setCount;
    private CashDispenser next;

    public DenominationDispenser(int denomination, IntSupplier getCount, IntConsumer setCount) {
        this.denomination = denomination;
        this.getCount = getCount;
        this.setCount = setCount;
    }

    @Override
    public void setNextDispenser(CashDispenser next) {
        this.next = next;
    }

    @Override
    public boolean canDispense(ATM atm, int amount) {
        int count = getCount.getAsInt();
        int notes = Math.min(amount / denomination, count);
        int remainder = amount - notes * denomination;
        return remainder == 0 || (next != null && next.canDispense(atm, remainder));
    }

    @Override
    public void dispense(ATM atm, int amount) {
        int count = getCount.getAsInt();
        int notes = Math.min(amount / denomination, count);
        setCount.accept(count - notes);

        int remainder = amount - notes * denomination;

        if (notes > 0) {
            System.out.println("Dispensed " + notes + " x " + denomination + " notes");
        }

        if (remainder > 0 && next != null) {
            next.dispense(atm, remainder);
        }
    }
}
