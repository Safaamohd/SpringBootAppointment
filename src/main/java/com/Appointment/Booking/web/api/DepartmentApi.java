package com.Appointment.Booking.web.api;

import com.Appointment.Booking.dto.DepartmentRequest;
import com.Appointment.Booking.dto.PatientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/api/department/")

public interface DepartmentApi {
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentRequest departmentRequest);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllDepartment();

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id")Long id);

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> editDepartment(@PathVariable("id")Long id, @RequestBody DepartmentRequest departmentRequest);
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable("id")Long id);

}
