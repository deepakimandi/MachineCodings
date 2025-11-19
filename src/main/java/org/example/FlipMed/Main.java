package org.example.FlipMed;

import org.example.FlipMed.dto.DoctorSlot;
import org.example.FlipMed.enums.Specialization;
import org.example.FlipMed.model.Booking;
import org.example.FlipMed.model.Doctor;
import org.example.FlipMed.model.Patient;
import org.example.FlipMed.repository.BookingRepository;
import org.example.FlipMed.repository.DoctorRepository;
import org.example.FlipMed.repository.PatientRepository;
import org.example.FlipMed.service.BookingService;
import org.example.FlipMed.service.DoctorService;
import org.example.FlipMed.service.PatientService;
import org.example.FlipMed.strategy.SlotRankStrategy;
import org.example.FlipMed.strategy.StartTimeRankStrategy;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Initialize repositories
        PatientRepository patientRepository = new PatientRepository();
        DoctorRepository doctorRepository = new DoctorRepository();
        BookingRepository bookingRepository = new BookingRepository();

        // Initialize services
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        BookingService bookingService = new BookingService(bookingRepository, doctorRepository, patientRepository);

        // Initialize ranking strategy
        SlotRankStrategy rankStrategy = new StartTimeRankStrategy();

        // Register doctors
        Doctor curious = doctorService.register("Curious", Specialization.CARDIOLOGIST, 4.5);
        Doctor dreadful = doctorService.register("Dreadful", Specialization.CARDIOLOGIST, 4.2);
        Doctor daring = doctorService.register("Daring", Specialization.DERMATOLOGIST, 4.8);

        // Declare availability
        doctorService.declareAvailability(curious.getId(), List.of("9:30", "12:30", "16:00"));
        doctorService.declareAvailability(dreadful.getId(), List.of("12:30", "13:00"));

        // Register patients
        Patient p1 = patientService.register("Shubh");
        Patient p2 = patientService.register("Kunal");

        // Search slots
        System.out.println("Available Cardiologist slots:");
        List<DoctorSlot> slots = bookingService.search(Specialization.CARDIOLOGIST, rankStrategy);
        for (DoctorSlot slot : slots) {
            System.out.println(slot.getDoctor().getName() + " - " + slot.getSlot());
        }

        // Book slots
        Booking b1 = bookingService.book(p1.getId(), curious.getId(), "12:30");

        // Bookings of Doctor Curious
        System.out.println("\nDoctor Curious bookings:");
        for (Booking b : bookingService.viewBookingsByDoctor(curious.getId())) {
            System.out.println("Booking: Patient ID " + patientService.findById(b.getPatientId()).getId());
        }

        // Try booking same slot for another patient
        try {
            Booking b2 = bookingService.book(p2.getId(), curious.getId(), "12:30");
        } catch (Exception e) {
            System.out.println("\nPatient 2 waitlisted: " + e.getMessage());
        }

        // Cancel booking and observe waitlist trigger
        bookingService.cancel(b1.getId());

        // Final bookings
        System.out.println("\nDoctor Curious bookings after cancellation:");
        for (Booking b : bookingService.viewBookingsByDoctor(curious.getId())) {
            System.out.println("Booking: Patient ID " + patientService.findById(b.getPatientId()).getId());
        }
    }
}
