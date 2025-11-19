package org.example.AmazonLockerSystem;

import lombok.Getter;
import org.example.AmazonLockerSystem.model.Locker;
import org.example.AmazonLockerSystem.service.LockerService;
import org.example.AmazonLockerSystem.service.NotificationService;
import org.example.AmazonLockerSystem.state.LockerState;

@Getter
public class LockerMachine {
    private final LockerService lockerService;
    private final PackageRepository packageRepository;
    private final OtpService otpService;
    private final NotificationService notificationService;

    private final Locker locker;
    private LockerState state;

    public LockerMachine(String name, LockerService lockerService, PackageRepository packageRepository) {

    }

    public void touch() {
        state.touch();
    }

    public void validateCode(String code) {
        state.validateCode(code, locker.getName());
    }

    public void closeDoor(String slotId) {
        state.closeDoor(locker.getName(), slotId);
    }

    public void selectCarrierEntry() {

    }
}
