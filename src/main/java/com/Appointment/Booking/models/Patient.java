package com.Appointment.Booking.models;

import com.Appointment.Booking.dto.PatientResponse;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientId")
    private Long id;
    @Column(name = "patientFirstName")
    private String patientFirstName;
    @Column(name = "patientLastName")
    private String patientLastName;
    @Column(name = "patientEmail")
    private String patientEmail;
    @Column(name = "patientMobileNo")
    private String patientMobileNo;
    @Column(name = "patientAddress")
    private String patientAddress;
    private String patientPassword;
    private int status;
    private String role;



}
