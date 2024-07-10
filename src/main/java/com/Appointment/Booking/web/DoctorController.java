package com.Appointment.Booking.web;

import com.Appointment.Booking.dto.DoctorRequest;
import com.Appointment.Booking.dto.PatientRequest;
import com.Appointment.Booking.models.Doctor;
import com.Appointment.Booking.services.DoctorService;
import com.Appointment.Booking.web.api.DoctorApi;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@Data
@RequestMapping("/api/doctor")


public class DoctorController implements DoctorApi {
    @Autowired
    private final DoctorService doctorService;

    @PostMapping("/loginDoctor")
    public ResponseEntity<Map<String,String>> login(@RequestBody Doctor doctor, HttpSession session) {
        Doctor foundDoctor = doctorService.findByDoctorEmail(doctor.getDoctorEmail());
        Map<String,String> response = new HashMap<>();

        if(foundDoctor == null){
            response.put("message","Doctor not found");
            return ResponseEntity.badRequest().body(response);

        }
        if(foundDoctor.getPassword().equals(doctor.getPassword())) {
            session.setAttribute("doctor",foundDoctor);
            response.put("message", "Login successfully");
            response.put("doctorId", String.valueOf(foundDoctor.getId()));
            response.put("role", foundDoctor.getRole());
            response.put("id", String.valueOf(foundDoctor.getId()));
            response.put("email", foundDoctor.getDoctorEmail());

            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Incorrect Login");
            return ResponseEntity.badRequest().body(response);
        }
    }
    public ResponseEntity<?> addDoctor(DoctorRequest doctorRequest) {
        return ResponseEntity.ok().body(doctorService.add(doctorRequest));
    }

    public ResponseEntity<?> getAllDoctor() {
        return ResponseEntity.ok().body(doctorService.getAll());
    }
    public ResponseEntity<?> getDoctorById(Long id) { return ResponseEntity.ok().body(doctorService.getById(id));}
    public ResponseEntity<?> editDoctor(Long id, DoctorRequest doctorRequest) {
        return ResponseEntity.ok().body(doctorService.edit(id, doctorRequest));
    }
    public ResponseEntity<?> deleteDoctor(Long id) {
        return ResponseEntity.ok().body(doctorService.delete(id));
    }


}
