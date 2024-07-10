package com.Appointment.Booking.repository;

import com.Appointment.Booking.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PatientRepo extends JpaRepository<Patient,Long> {
    Patient findByPatientEmail(String patientEmail);

}
