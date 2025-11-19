package org.example.ATMMachine.enums;

public enum Denomination {
    BILL_2000(2000),
    BILL_500(500),
    BILL_100(100);

    public final int value;

    Denomination(int value) {
        this.value = value;
    }
}
