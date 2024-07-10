package com.Appointment.Booking.services;

import com.Appointment.Booking.dto.DepartmentRequest;
import com.Appointment.Booking.dto.DepartmentResponse;
import com.Appointment.Booking.dto.PatientRequest;
import com.Appointment.Booking.dto.PatientResponse;
import com.Appointment.Booking.models.Department;
import com.Appointment.Booking.models.Patient;
import com.Appointment.Booking.repository.DepartmentRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service


public class DepartmentService {
    private final ModelMapper modelMapper;
    private final DepartmentRepo departmentRepo;
    public DepartmentResponse add(DepartmentRequest departmentRequest) {
        Department department = modelMapper.map(departmentRequest, Department.class);
        department.setStatus(1);
        departmentRepo.save(department);
        return modelMapper.map(department,DepartmentResponse.class);
    }

    public List<DepartmentResponse> getAll() {
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentResponse> departmentResponses = departments.stream()
                .map(department -> modelMapper.map(department,DepartmentResponse.class))
                .collect(Collectors.toList());
        return departmentResponses;
    }
    public DepartmentResponse getById(Long departmentId) {
        Optional<Department> departmentOptional = departmentRepo.findById(departmentId);
        if (departmentOptional.isPresent()) {
            return modelMapper.map(departmentOptional.get(), DepartmentResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<?> edit(Long departmentId, DepartmentRequest departmentRequest) {
        Optional<Department> departmentOptional = departmentRepo.findById(departmentId);
        if(departmentOptional.isPresent()) {
            Department existingDepartment = departmentOptional.get();
            modelMapper.map(departmentRequest, existingDepartment);
            existingDepartment.setId(departmentId);
            departmentRepo.save(existingDepartment);
            return ResponseEntity.ok().body(modelMapper.map(existingDepartment, DepartmentResponse.class));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> delete(Long departmentId) {
        Optional<Department> departmentOptional = departmentRepo.findById(departmentId);
        if (departmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Department department = departmentOptional.get();
        if (department.getStatus() == 1) {
            department.setStatus(0);
            departmentRepo.save(department);
            Map<String, Object> res = new HashMap<>();
            res.put("response", Boolean.TRUE);
            return ResponseEntity.ok().body(res);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

}
