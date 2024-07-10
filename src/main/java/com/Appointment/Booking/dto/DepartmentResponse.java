package com.Appointment.Booking.dto;

import lombok.Data;

@Data

public class DepartmentResponse {
    private Long id;
    private String departmentName;
    private String description;
    private int status;
}
