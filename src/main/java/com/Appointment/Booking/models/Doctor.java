package com.Appointment.Booking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorId")
    private Long id;
    @Column(name = "doctorName")
    private String doctorName;
    @Column(name = "doctorEmail")
    private String doctorEmail;
    @Column(name = "doctorMobileNo")
    private String doctorMobileNo;
    private String experience;
    private String location;
    private String rating;
    private String schedule;
    private String cost;
    private int Status;
    private String Password;
    private String role;
    private String imageUrl;
    @ManyToOne
    private Department department;



}
