package org.example.ATMMachine.repository;

import org.example.ATMMachine.enums.ATMStatus;
import org.example.ATMMachine.model.ATM;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ATMRepository {

    private final Map<String, ATM> atms = new HashMap<>();

    // Save a new ATM
    public void save(ATM atm) {
        atms.put(atm.getId(), atm);
    }

    // Get ATM by ID
    public Optional<ATM> getById(String id) {
        return Optional.ofNullable(atms.get(id));
    }

    // Update ATM status by ID
    public void updateATMStatusById(String id, ATMStatus newStatus) {
        ATM atm = atms.get(id);
        if (atm != null) {
            atm.setStatus(newStatus);
        }
    }
}
