package org.example.FlipMed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.FlipMed.model.Doctor;

@Getter
@AllArgsConstructor
public class DoctorSlot {
    private final Doctor doctor;
    private final String slot;
}
