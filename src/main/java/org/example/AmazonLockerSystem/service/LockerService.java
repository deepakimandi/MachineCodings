package org.example.AmazonLockerSystem.service;

import org.example.AmazonLockerSystem.model.Locker;
import org.example.AmazonLockerSystem.model.PackageSize;
import org.example.AmazonLockerSystem.model.Slot;
import org.example.AmazonLockerSystem.repository.LockerRepository;

import java.util.*;
import java.util.stream.Collectors;

public class LockerService {

    private final LockerRepository lockerRepository;
    private final SlotAssignmentStrategy slotStrategy;

    public LockerService(LockerRepository lockerRepository, SlotAssignmentStrategy slotStrategy) {
        this.lockerRepository = lockerRepository;
        this.slotStrategy = slotStrategy;
    }

    public void save(Locker locker) {
        lockerRepository.save(locker);
    }

    public Locker getLockerByName(String name) {
        return lockerRepository.getLockerByName(name);
    }

    public List<Locker> getEligibleLockersByZipAndSize(String zip, PackageSize pkgSize) {
        List<Locker> lockers = lockerRepository.getLockersByZip(zip);
        return lockers.stream()
                .filter(locker -> locker.getAllSlots().stream()
                        .anyMatch(slot -> slot.getSize().canFit(pkgSize)))
                .collect(Collectors.toList());
    }

    public List<Slot> getEligibleSlotsForLocker(Locker locker, PackageSize pkgSize) {
        return locker.getAllSlots().stream()
                .filter(Slot::isAvailable)
                .filter(slot -> slot.getSize().canFit(pkgSize))
                .collect(Collectors.toList());
    }

    public void reserveSlotForPackage(Locker chosenLocker, Package pkg) {
        List<Slot> eligibleSlots = getEligibleSlotsForLocker(chosenLocker, pkg.getPackageSize());
        Slot reservedSlot = slotStrategy.assignSlot(eligibleSlots);

        if (reservedSlot == null) {
            throw new RuntimeException("No available slot found for package in locker: " + chosenLocker.getName());
        }

        pkg.setLockerName(chosenLocker.getName());
        pkg.setSlotId(reservedSlot.getSlotId());
        reservedSlot.reserveFor(pkg);

        System.out.println("Reserved slot: " + reservedSlot.getSlotId());
    }
}
