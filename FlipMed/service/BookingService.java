package org.example.FlipMed.service;

import lombok.AllArgsConstructor;
import org.example.FlipMed.Exception.BookingNotFoundException;
import org.example.FlipMed.dto.DoctorSlot;
import org.example.FlipMed.enums.Specialization;
import org.example.FlipMed.model.Booking;
import org.example.FlipMed.model.Doctor;
import org.example.FlipMed.repository.BookingRepository;
import org.example.FlipMed.repository.DoctorRepository;
import org.example.FlipMed.repository.PatientRepository;
import org.example.FlipMed.strategy.SlotRankStrategy;

import java.util.*;

@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public List<DoctorSlot> search(Specialization spec, SlotRankStrategy strategy) {
        List<Doctor> doctors = doctorRepo.findBySpecialization(spec);
        List<DoctorSlot> result = new ArrayList<>();

        for (Doctor d : doctors) {
            for (Map.Entry<String, Boolean> entry : d.getAvailability().entrySet()) {
                if (entry.getValue()) { // only available slots
                    result.add(new DoctorSlot(d, entry.getKey()));
                }
            }
        }

        return strategy.rank(result);
    }

    public Booking book(UUID patientId, UUID doctorId, String slot) {
        Doctor doctor = doctorRepo.findById(doctorId);
        if (doctor == null) throw new RuntimeException("Doctor not found");

        Map<String, Boolean> availability = doctor.getAvailability();

        // Slot not declared
        if (!availability.containsKey(slot)) {
            throw new RuntimeException("Invalid slot: Doctor has not declared availability for this time");
        }

        // Patient already has a booking in this slot
        for (Booking b : bookingRepo.findByPatient(patientId)) {
            if (b.getSlot().equals(slot)) {
                throw new RuntimeException("Patient already has an appointment at this time");
            }
        }

        // Book if slot is available
        if (availability.get(slot)) {
            Booking booking = new Booking(patientId, doctorId, slot);
            bookingRepo.save(booking);
            availability.put(slot, false); // mark slot as booked
            System.out.println("\n" + patientRepo.findById(patientId).getName() + " booked a slot with Dr. " + doctor.getName() + " at " + slot);
            return booking;
        } else {
            // Add to waitlist if valid but booked
            String key = doctorId.toString() + "_" + slot;
            bookingRepo.addToWaitlist(key, patientId);
            throw new RuntimeException("Slot already booked. Added to waitlist.");
        }
    }

    public void cancel(UUID bookingId) {
        Booking booking = bookingRepo.getBookingById(bookingId);
        if (booking == null) throw new BookingNotFoundException("Booking not found");

        Doctor doctor = doctorRepo.findById(booking.getDoctorId());
        if (doctor != null) {
            doctor.getAvailability().put(booking.getSlot(), true); // Mark slot as available
        }

        bookingRepo.delete(booking);
        System.out.println("\n" + patientRepo.findById(booking.getPatientId()).getName() + " canceled their booking");

        // Promote first patient in waitlist
        String key = doctor.getId().toString() + "_" + booking.getSlot();
        UUID nextPatient = bookingRepo.popFromWaitlist(key);
        if (nextPatient != null) {
            book(nextPatient, doctor.getId(), booking.getSlot());
        }
    }

    public List<Booking> viewBookingsByDoctor(UUID doctorId) {
        return bookingRepo.findByDoctor(doctorId);
    }

    public List<Booking> viewBookingsByPatient(UUID patientId) {
        return bookingRepo.findByPatient(patientId);
    }
}
