package org.example.AmazonLockerSystem.model;

import lombok.Data;
import org.example.AmazonLockerSystem.enums.LockerStatus;

import java.util.List;
import java.util.Map;

@Data
public class Locker {

    private String name;
    private LockerStatus status;
    private String zipCode;
    private Map<String, Slot> slots;

    public Slot getSlotById(String id) {
        return slots.get(id);
    }

    public List<Slot> getAllSlots() {
        return (List<Slot>) slots.values();
    }
}
