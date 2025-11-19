package org.example.FlipMed.strategy;

import org.example.FlipMed.dto.DoctorSlot;

import java.util.List;

public interface SlotRankStrategy {
    List<DoctorSlot> rank(List<DoctorSlot> slots);
}