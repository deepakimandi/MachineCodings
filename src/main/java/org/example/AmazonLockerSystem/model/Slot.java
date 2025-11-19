package org.example.AmazonLockerSystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;

public class Slot {
    private final AtomicBoolean available = new AtomicBoolean(true);
    @Getter private final SlotSize size;
    @Setter private Package storedPackage;

    public Slot(SlotSize size) {
        this.size = size;
    }

    public boolean isAvailable() {
        return available.get();
    }

    public boolean acquire() {
        return available.compareAndSet(true, false);
    }

    public void release() {
        available.set(true);
        storedPackage = null;
    }

}
