package org.example.AmazonLockerSystem.repository;

import org.example.AmazonLockerSystem.model.Locker;

import java.util.*;

public class LockerRepository {
    private final Map<String, Locker> lockers = new HashMap<>();
    private final Map<String, List<Locker>> zipToLockersMap = new HashMap<>();

    public void save(Locker locker) {
        // Assuming Locker has getName() and getZip() methods
        lockers.put(locker.getName(), locker);

        zipToLockersMap
                .computeIfAbsent(locker.getZip(), k -> new ArrayList<>())
                .add(locker);
    }

    public Locker getLockerByName(String name) {
        return lockers.get(name);
    }

    public List<Locker> getLockersByZip(String zip) {
        return zipToLockersMap.getOrDefault(zip, Collections.emptyList());
    }
}
