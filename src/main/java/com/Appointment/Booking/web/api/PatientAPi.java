package com.Appointment.Booking.web.api;

import com.Appointment.Booking.dto.PatientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/api/patient/")

public interface PatientAPi {
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addPatient(@RequestBody PatientRequest patientRequest);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllPatient();

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getPatientById(@PathVariable("id")Long id);
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> editPatient(@PathVariable("id")Long id, @RequestBody PatientRequest patientRequest);
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePatient(@PathVariable("id")Long id);
}
