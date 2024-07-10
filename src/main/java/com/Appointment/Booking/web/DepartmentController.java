package com.Appointment.Booking.web;

import com.Appointment.Booking.dto.DepartmentRequest;
import com.Appointment.Booking.dto.PatientRequest;
import com.Appointment.Booking.services.DepartmentService;
import com.Appointment.Booking.web.api.DepartmentApi;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Data

public class DepartmentController implements DepartmentApi {
    private final DepartmentService departmentService;
    public ResponseEntity<?> addDepartment( DepartmentRequest departmentRequest) {
        return ResponseEntity.ok().body(departmentService.add(departmentRequest));
    }

    public ResponseEntity<?> getAllDepartment() {
        return ResponseEntity.ok().body(departmentService.getAll());
    }
    public ResponseEntity<?> getDepartmentById(Long id) {

        return ResponseEntity.ok().body(departmentService.getById(id));}
    public ResponseEntity<?> editDepartment(Long id, DepartmentRequest departmentRequest) {
        return ResponseEntity.ok().body(departmentService.edit(id, departmentRequest));
    }
    public ResponseEntity<?> deleteDepartment(Long id) {
        return ResponseEntity.ok().body(departmentService.delete(id));
    }


    }
