package com.Appointment.Booking.web.api;

import com.Appointment.Booking.dto.AppointmentRequest;
import com.Appointment.Booking.dto.DoctorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping ("/api/appointment/")

public interface AppointmentApi {

    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentRequest appointmentRequest);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllAppointment();

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAppointmentById(@PathVariable("id")Long id);
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> editAppointment(@PathVariable("id")Long id, @RequestBody AppointmentRequest appointmentRequest);
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAppointment(@PathVariable("id")Long id);

}
