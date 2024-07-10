package com.Appointment.Booking.services;

import com.Appointment.Booking.dto.PatientRequest;
import com.Appointment.Booking.dto.PatientResponse;
import com.Appointment.Booking.models.Patient;
import com.Appointment.Booking.repository.PatientRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Service


public class PatientService {
    @Autowired
    private final PatientRepo patientRepo;

    @Autowired
    private final ModelMapper modelMapper;

    public PatientResponse add(PatientRequest patientRequest) {
        Patient patient = modelMapper.map(patientRequest, Patient.class);
        patient.setRole("patient");
        patient.setStatus(1);
        patientRepo.save(patient);
        return modelMapper.map(patient,PatientResponse.class);
    }

    public List<PatientResponse> getAll() {
        List<Patient> patients = patientRepo.findAll();
        List<PatientResponse> patientResponses = patients.stream()
                .map(patient -> modelMapper.map(patient,PatientResponse.class))
                .collect(Collectors.toList());
        return patientResponses;
    }
    public PatientResponse getById(Long patientId) {
        Optional<Patient> patientOptional = patientRepo.findById(patientId);
        if (patientOptional.isPresent()) {
            return modelMapper.map(patientOptional.get(), PatientResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<?> edit(Long patientId, PatientRequest patientRequest) {
        Optional<Patient> patientOptional = patientRepo.findById(patientId);
        if(patientOptional.isPresent()) {
            Patient existingPatient = patientOptional.get();
            modelMapper.map(patientRequest, existingPatient);
            existingPatient.setId(patientId);
            patientRepo.save(existingPatient);
            return ResponseEntity.ok().body(modelMapper.map(existingPatient, PatientResponse.class));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> delete(Long patientId) {
        Optional<Patient> patientOptional = patientRepo.findById(patientId);
        if (patientOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Patient patient = patientOptional.get();
        if (patient.getStatus() == 1) {
            patient.setStatus(0);
            patientRepo.save(patient);
            Map<String, Object> res = new HashMap<>();
            res.put("response", Boolean.TRUE);
            return ResponseEntity.ok().body(res);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
    public Patient findByPatientEmail(String patientEmail) {
        return patientRepo.findByPatientEmail(patientEmail);

    }
}

