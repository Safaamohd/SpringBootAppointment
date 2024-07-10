package com.Appointment.Booking.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data

public class DepartmentRequest {
    private String departmentName;
    private String description;
}
