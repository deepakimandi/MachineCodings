package org.example.AmazonLockerSystem.state;

import lombok.AllArgsConstructor;
import org.example.AmazonLockerSystem.LockerMachine;

@AllArgsConstructor
public class IdleState implements LockerState {
    private final LockerMachine machine;

    @Override
    public void touch() {
        System.out.println("Screen touched -> switching to CUSTOMER_PICKUP");
        machine.setState(new CustomerPickupState(machine));
    }

    @Override
    public void validateCode(String code, String lockerName) {

    }

    @Override
    public void closeDoor(String lockerName, String slotId) {

    }

    @Override
    public void selectCarrierEntry() {

    }

    @Override
    public void selectOption(String option) {

    }

    @Override
    public LockerStatus getStatus() {
        return null;
    }
}
