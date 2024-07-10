package com.Appointment.Booking.dto;

import lombok.Data;

@Data

public class PatientRequest {
    private String patientFirstName;

    private String patientLastName;

    private String patientEmail;

    private String patientMobileNo;

    private String patientAddress;
    private String patientPassword;
    private String role = "patient";


}
