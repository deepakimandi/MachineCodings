package org.example.FlipMed.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Booking {
    private final UUID id;
    private final UUID patientId;
    private final UUID doctorId;
    private final String slot;

    public Booking(UUID patientId, UUID doctorId, String slot) {
        this.id = UUID.randomUUID();
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.slot = slot;
    }
}
