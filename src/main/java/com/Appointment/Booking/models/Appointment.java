package com.Appointment.Booking.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentId")
    private Long id;
    private int status;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;

}
