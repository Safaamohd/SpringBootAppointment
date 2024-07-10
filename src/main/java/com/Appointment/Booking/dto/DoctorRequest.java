package com.Appointment.Booking.dto;

import lombok.Data;

@Data

public class DoctorRequest {
    private String doctorName;

    private String doctorEmail;

    private String doctorMobileNo;
    private String experience;
    private String location;
    private String rating;
    private String schedule;
    private String cost;
    private String Password;
    private Long department;
    private String imageUrl;
    private String role = "doctor";
}
