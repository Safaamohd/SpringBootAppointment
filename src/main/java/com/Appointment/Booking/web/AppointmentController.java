package com.Appointment.Booking.web;

import com.Appointment.Booking.dto.AppointmentRequest;
import com.Appointment.Booking.dto.DoctorRequest;
import com.Appointment.Booking.models.Appointment;
import com.Appointment.Booking.services.AppointmentService;
import com.Appointment.Booking.web.api.AppointmentApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Data
@RequestMapping("/add")

public class AppointmentController  {
    @Autowired
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

//    @PostMapping("/addAppointment/")
//    public ResponseEntity<?> patientAppointment(@RequestBody Map<String, Object> requestData) {
//        Long patientId = Long.valueOf(requestData.get("patientId").toString());
//        Long doctorId = Long.valueOf(requestData.get("doctorId").toString());
//         boolean result = appointmentService.addPatientAppointment(patientId, doctorId);
//         if (result) {
//             return ResponseEntity.ok().body("Appointment booked successfully!");
//         } else {
//             return ResponseEntity.badRequest().body("Error booking appointment!");
//         }
//
//    }
//    @PostMapping("/addAppointment/")
//    public ResponseEntity<?> addAppointment(@RequestBody AppointmentRequest appointmentRequest) {
//        return ResponseEntity.ok().body(appointmentService.add(appointmentRequest));
//    }
@PostMapping("/addAppointment/")
public ResponseEntity<?> patientAppointment(@RequestBody Map<String, Object> requestData) {
    if (!requestData.containsKey("patientId") || !requestData.containsKey("doctorId")) {
        return ResponseEntity.badRequest().body("Missing patientId or doctorId");
    }

    Object patientIdObj = requestData.get("patientId");
    Object doctorIdObj = requestData.get("doctorId");

    if (patientIdObj == null || doctorIdObj == null) {
        return ResponseEntity.badRequest().body("patientId or doctorId is null");
    }

    Long patientId;
    Long doctorId;
    try {
        patientId = Long.valueOf(patientIdObj.toString());
        doctorId = Long.valueOf(doctorIdObj.toString());
    } catch (NumberFormatException e) {
        return ResponseEntity.badRequest().body("Invalid patientId or doctorId format");
    }

    boolean result = appointmentService.addPatientAppointment(patientId, doctorId);
    if (result) {
        return ResponseEntity.ok().body("Appointment booked successfully!");
    } else {
        return ResponseEntity.badRequest().body("Error booking appointment!");
    }
}

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppointment() {
        return ResponseEntity.ok().body(appointmentService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(Long id) { return ResponseEntity.ok().body(appointmentService.getById(id));}

    @PutMapping("/{id}")
    public ResponseEntity<?> editAppointment(Long id, AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok().body(appointmentService.edit(id, appointmentRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(Long id) {
        return ResponseEntity.ok().body(appointmentService.delete(id));
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }
}
