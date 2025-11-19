package org.example.FlipMed.repository;

import org.example.FlipMed.enums.Specialization;
import org.example.FlipMed.model.Doctor;

import java.util.*;

public class DoctorRepository {
    private final Map<UUID, Doctor> doctorMap = new HashMap<>();

    public void save(Doctor doctor) {
        doctorMap.put(doctor.getId(), doctor);
    }

    public Doctor findById(UUID id) {
        return doctorMap.get(id);
    }

    public List<Doctor> findBySpecialization(Specialization specialization) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doc : doctorMap.values()) {
            if (doc.getSpecialization() == specialization) {
                result.add(doc);
            }
        }
        return result;
    }
}
