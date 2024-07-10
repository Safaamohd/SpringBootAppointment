package com.Appointment.Booking.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data

public class PatientResponse {
    private Long id;

    private String patientFirstName;

    private String patientLastName;

    private String patientEmail;

    private String patientMobileNo;

    private String patientAddress;
    private String patientPassword;

    private int status;
}
