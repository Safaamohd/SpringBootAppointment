package com.Appointment.Booking.repository;

import com.Appointment.Booking.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
