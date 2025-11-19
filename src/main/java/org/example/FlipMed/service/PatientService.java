package org.example.FlipMed.service;

import lombok.AllArgsConstructor;
import org.example.FlipMed.Exception.PatientNotFoundException;
import org.example.FlipMed.model.Patient;
import org.example.FlipMed.repository.PatientRepository;

import java.util.UUID;

@AllArgsConstructor
public class PatientService {
    private final PatientRepository repo;

    public Patient register(String name) {
        Patient p = new Patient(name);
        repo.save(p);
        return p;
    }

    public Patient findById(UUID id) {
        Patient patient = repo.findById(id);
        if (patient == null) {
            throw new PatientNotFoundException("Patient not found");
        }
        return patient;
    }
}
