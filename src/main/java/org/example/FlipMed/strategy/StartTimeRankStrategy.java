package org.example.FlipMed.strategy;

import org.example.FlipMed.dto.DoctorSlot;
import org.example.FlipMed.utils.Utils;

import java.util.Comparator;
import java.util.List;

public class StartTimeRankStrategy implements SlotRankStrategy {

    @Override
    public List<DoctorSlot> rank(List<DoctorSlot> slots) {
        slots.sort(Comparator.comparing(slot -> Utils.convertStringToLocalTime(slot.getSlot())));
        return slots;
    }
}
