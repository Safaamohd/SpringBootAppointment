package com.Appointment.Booking.web.api;

import com.Appointment.Booking.dto.DoctorRequest;
import com.Appointment.Booking.dto.PatientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/api/doctor/")

public interface DoctorApi {
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addDoctor(@RequestBody DoctorRequest doctorRequest);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllDoctor();

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getDoctorById(@PathVariable("id")Long id);
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> editDoctor(@PathVariable("id")Long id, @RequestBody DoctorRequest doctorRequest);
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDoctor(@PathVariable("id")Long id);
}
