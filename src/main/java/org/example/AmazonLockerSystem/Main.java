package org.example.AmazonLockerSystem;

import org.example.AmazonLockerSystem.model.Package;
import org.example.AmazonLockerSystem.model.PackageSize;
import org.example.AmazonLockerSystem.repository.LockerRepository;
import org.example.AmazonLockerSystem.service.LockerService;

public class Main {
    public static void main(String[] args) {

        // Initialize repositories & services (assumed existing classes)
        LockerRepository lockerRepo = new LockerRepository();
        PackageRepository packageRepo = new PackageRepository();
        AgentRepository agentRepo = new AgentRepository();
        OtpRepository otpRepo = new OtpRepository();

        SlotAssignmentStrategy slotStrategy = new FirstFitSlotStrategy();
        AgentAssignmentStrategy agentStrategy = new RandomAgentAssignmentStrategy();

        LockerService lockerService = new LockerService(lockerRepo, slotStrategy);
        AgentService agentService = new AgentService(agentRepo, agentStrategy, new NotificationService(null, agentRepo));
        OtpService otpService = new OtpService(otpRepo);
        NotificationService notificationService = new NotificationService(null, agentRepo);

        // Assume pkg defined earlier...
        Package pkg = new Package("PKG1", new PackageSize(), "CUST123");

        // -------------------------------------------------------------
        // 1) Customer selects locker by ZIP → choose one eligible
        // -------------------------------------------------------------
        List<Locker> eligibleLockers =
                lockerService.getEligibleLockersByZipAndSize("560001", pkg.getPackageSize());

        if (eligibleLockers.isEmpty()) {
            throw new RuntimeException("No eligible lockers available!");
        }

        Locker chosenLocker = eligibleLockers.get(0);
        System.out.println("Customer chose locker: " + chosenLocker.getName());


        // -------------------------------------------------------------
        // 2) Place order
        // a. reserve a slot using slot strategy
        // -------------------------------------------------------------
        lockerService.reserveSlotForPackage(chosenLocker, pkg);
        System.out.println();


        // -------------------------------------------------------------
        // b. assign a delivery agent using agent strategy
        // -------------------------------------------------------------
        agentService.assignAgentForDelivery(chosenLocker, pkg);
        System.out.println();


        // -------------------------------------------------------------
        // 3) Locker machine for that locker
        // -------------------------------------------------------------
        LockerMachine machine = new LockerMachine(
                chosenLocker.getName(),
                lockerService,
                packageRepo,
                otpService,
                notificationService
        );


        // -------------------------------------------------------------
        // 4) Agent delivers package
        // -------------------------------------------------------------
        pkg.setStatus(PackageStatus.OUT_FOR_DELIVERY);

        machine.touch();
        machine.selectCarrierEntry();
        machine.selectOption("DROP_PACKAGE");
        machine.validateCode(pkg.getPackageId());
        machine.closeDoor(pkg.getSlotId());

        System.out.println();


        // -------------------------------------------------------------
        // 5) Customer pickup
        // -------------------------------------------------------------
        machine.touch();

        // sample OTP extraction from repository (real logic may vary)
        String otp = otpRepo.getAllOtps()
                .keySet()
                .iterator()
                .next()
                .split(":")[1];

        machine.validateCode(otp);
        machine.closeDoor(pkg.getSlotId());

        System.out.println("Final package status: " + pkg.getStatus());
    }
}
