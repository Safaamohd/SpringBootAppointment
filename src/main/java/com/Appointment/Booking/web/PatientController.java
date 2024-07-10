package com.Appointment.Booking.web;

import com.Appointment.Booking.dto.PatientRequest;

import com.Appointment.Booking.models.Patient;
import com.Appointment.Booking.services.PatientService;
import com.Appointment.Booking.web.api.PatientAPi;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@Data
@RequestMapping("/api/patient")


public class PatientController implements PatientAPi {
    @Autowired
    private final PatientService patientService;

    @PostMapping("/")
    public ResponseEntity<?> register(@RequestBody PatientRequest patientRequest) {
        return ResponseEntity.ok().body(patientService.add(patientRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Patient patient, HttpSession session) {
        Patient foundPatient = patientService.findByPatientEmail(patient.getPatientEmail());
        Map<String,String> response = new HashMap<>();

        if(foundPatient == null){
            response.put("message","Patient not found");
            return ResponseEntity.badRequest().body(response);

        }
        if(foundPatient.getPatientPassword().equals(patient.getPatientPassword())) {
            session.setAttribute("patient",foundPatient);
            response.put("message", "Login successfully");
            response.put("patientId", String.valueOf(foundPatient.getId()));
            response.put("role", foundPatient.getRole());
            response.put("id", String.valueOf(foundPatient.getId()));
            response.put("email", foundPatient.getPatientEmail());

            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Incorrect Login");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("/checkSession")
    public ResponseEntity<Map<String, String>> checkSession(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        if (session.getAttribute("user") != null) {
            response.put("message", "User is logged in");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "User is not logged in");
            return ResponseEntity.status(401).body(response);
        }
    }

    public ResponseEntity<?> addPatient( PatientRequest patientRequest) {
//        return new ResponseEntity(patientService.add(patientRequest), HttpStatus.OK);
        return ResponseEntity.ok().body(patientService.add(patientRequest));
    }

    public ResponseEntity<?> getAllPatient() {
        return ResponseEntity.ok().body(patientService.getAll());
//        return new ResponseEntity(patientService.getAll(), HttpStatus.OK);
    }
    public ResponseEntity<?> getPatientById(Long id) {
        return ResponseEntity.ok().body(patientService.getById(id));
    }
    public ResponseEntity<?> editPatient(Long id, PatientRequest patientRequest) {
        return ResponseEntity.ok().body(patientService.edit(id, patientRequest));
    }
    public ResponseEntity<?> deletePatient(Long id) {
        return ResponseEntity.ok().body(patientService.delete(id));
    }



}