package com.Appointment.Booking.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class AppointmentRequest {
    private Long patient;
    private Long doctor;



}


