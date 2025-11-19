package org.example.FlipMed.strategy;

import org.example.FlipMed.dto.DoctorSlot;

import java.util.List;

public class RatingBasedRankStrategy implements SlotRankStrategy {

    @Override
    public List<DoctorSlot> rank(List<DoctorSlot> slots) {
        slots.sort((a, b) -> Double.compare(b.getDoctor().getRating(), a.getDoctor().getRating()));
        return slots;
    }
}
