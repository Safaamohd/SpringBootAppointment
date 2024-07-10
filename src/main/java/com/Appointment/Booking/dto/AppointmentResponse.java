package com.Appointment.Booking.dto;

import lombok.Data;

@Data

public class AppointmentResponse {
    private Long id;
    private int status;
    private PatientResponse patient;
    private DoctorResponse doctor;
}
