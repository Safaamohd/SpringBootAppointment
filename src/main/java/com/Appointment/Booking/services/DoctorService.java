package com.Appointment.Booking.services;

import com.Appointment.Booking.dto.*;
import com.Appointment.Booking.models.Department;
import com.Appointment.Booking.models.Doctor;
import com.Appointment.Booking.models.Patient;
import com.Appointment.Booking.repository.DepartmentRepo;
import com.Appointment.Booking.repository.DoctorRepo;
import com.Appointment.Booking.web.api.DoctorApi;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DoctorService {
    private final ModelMapper modelMapper;

    @Autowired
    private final DoctorRepo doctorRepo;

    @Autowired
    private final DepartmentRepo departmentRepo;

    public DoctorResponse add(DoctorRequest doctorRequest) {
        Optional<Department> departmentOptional = departmentRepo.findById(doctorRequest.getDepartment());
        if (departmentOptional.isPresent()) {
            Doctor doctor = modelMapper.map(doctorRequest, Doctor.class);
            doctor.setDepartment(departmentOptional.get());
            doctor.setRole("doctor");
            doctor.setStatus(1);
            doctorRepo.save(doctor);
            DoctorResponse doctorResponse = modelMapper.map(doctor, DoctorResponse.class);
            doctorResponse.setDepartment(modelMapper.map(departmentOptional.get(), DoctorResponse.class).getDepartment());
            return doctorResponse;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<DoctorResponse> getAll() {
        List<Doctor> doctors = doctorRepo.findAll();
        List<DoctorResponse> doctorResponses = doctors.stream()
                .map(doctor -> modelMapper.map(doctor,DoctorResponse.class))
                .collect(Collectors.toList());
        return doctorResponses;
    }

    public DoctorResponse getById(Long doctorId) {
        Optional<Doctor> patientOptional = doctorRepo.findById(doctorId);
        if (patientOptional.isPresent()) {
            return modelMapper.map(patientOptional.get(), DoctorResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<?> edit(Long doctorId, DoctorRequest doctorRequest) {
        Optional<Doctor> doctorOptional = doctorRepo.findById(doctorId);
        if(doctorOptional.isPresent()) {
            Doctor existingDoctor = doctorOptional.get();
            modelMapper.map(doctorRequest, existingDoctor);
            existingDoctor.setId(doctorId);
            doctorRepo.save(existingDoctor);
            return ResponseEntity.ok().body(modelMapper.map(existingDoctor, DoctorResponse.class));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> delete(Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepo.findById(doctorId);
        if (doctorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Doctor doctor= doctorOptional.get();
        if (doctor.getStatus() == 1) {
            doctor.setStatus(0);
            doctorRepo.save(doctor);
            Map<String, Object> res = new HashMap<>();
            res.put("response", Boolean.TRUE);
            return ResponseEntity.ok().body(res);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    public  Doctor findByDoctorEmail(String doctorEmail) {
        return doctorRepo.findByDoctorEmail(doctorEmail);
    }

}

