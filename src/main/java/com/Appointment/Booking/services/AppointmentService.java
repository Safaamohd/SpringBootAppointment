package com.Appointment.Booking.services;

import com.Appointment.Booking.dto.*;
import com.Appointment.Booking.models.Appointment;
import com.Appointment.Booking.models.Doctor;
import com.Appointment.Booking.models.Patient;
import com.Appointment.Booking.repository.AppointmentRepo;
import com.Appointment.Booking.repository.DoctorRepo;
import com.Appointment.Booking.repository.PatientRepo;
import jakarta.transaction.Transactional;
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
public class AppointmentService {

    @Autowired
    private final AppointmentRepo appointmentRepo;

    private final ModelMapper modelMapper;
    @Autowired
    private final DoctorRepo doctorRepo;
    @Autowired
    private final PatientRepo patientRepo;

//    public AppointmentResponse addPatientAppointment(AppointmentRequest appointmentRequest) {
//        Optional<Doctor> doctorOptional = doctorRepo.findById(appointmentRequest.getDoctor());
//        if (doctorOptional.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        Optional<Patient> patientOptional = patientRepo.findById(appointmentRequest.getPatient());
//        if (patientOptional.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        Appointment appointment = modelMapper.map(appointmentRequest, Appointment.class);
//        appointment.setDoctor(doctorOptional.get());
//        appointment.setPatient(patientOptional.get());
//        appointment.setStatus(1);
//        appointmentRepo.save(appointment);
//        AppointmentResponse appointmentResponse = modelMapper.map(appointment, AppointmentResponse.class);
//        appointmentResponse.setDoctor(modelMapper.map(doctorOptional.get(), DoctorResponse.class));
//        appointmentResponse.setPatient(modelMapper.map(patientOptional.get(), PatientResponse.class));
//
//        return appointmentResponse;
//    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }

    public List<AppointmentResponse> getAll() {
        List<Appointment> appointments = appointmentRepo.findAll();
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> {
                    AppointmentResponse response = modelMapper.map(appointment, AppointmentResponse.class);
                    response.setPatient(modelMapper.map(appointment.getPatient(), PatientResponse.class));
                    response.setDoctor(modelMapper.map(appointment.getDoctor(), DoctorResponse.class));
                    return response;
                })
//                        modelMapper.map(appointment,AppointmentResponse.class))
                .collect(Collectors.toList());
        return appointmentResponses;
    }

    public AppointmentResponse getById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepo.findById(appointmentId);
        if (appointmentOptional.isPresent()) {
            return modelMapper.map(appointmentOptional.get(), AppointmentResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> edit(Long appointmentId, AppointmentRequest appointmentRequest) {
        Optional<Appointment> appointmentOptional = appointmentRepo.findById(appointmentId);
        if (appointmentOptional.isPresent()) {
            Appointment existingAppointment = appointmentOptional.get();
            modelMapper.map(appointmentRequest, existingAppointment);
            existingAppointment.setId(appointmentId);
            appointmentRepo.save(existingAppointment);
            return ResponseEntity.ok().body(modelMapper.map(existingAppointment, AppointmentResponse.class));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> delete(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepo.findById(appointmentId);
        if (appointmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Appointment appointment = appointmentOptional.get();
        if (appointment.getStatus() == 1) {
            appointment.setStatus(0);
            appointmentRepo.save(appointment);
            Map<String, Object> res = new HashMap<>();
            res.put("response", Boolean.TRUE);
            return ResponseEntity.ok().body(res);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }


    @Transactional
    public boolean addPatientAppointment(Long patientId, Long doctorId) {
        Optional<Patient> patientOptional = patientRepo.findById(patientId);
        Optional<Doctor> doctorOptional = doctorRepo.findById(doctorId);

        if (patientOptional.isPresent() && doctorOptional.isPresent()) {
            Patient patient = patientOptional.get();
            Doctor doctor = doctorOptional.get();
            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            appointment.setStatus(1);
            appointmentRepo.save(appointment);
            return true;
        } else {
            return false;
        }

    }

}

