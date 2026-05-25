package com.hospital.hospitalbackend.controller;

import com.hospital.hospitalbackend.dto.PatientDTO;
import com.hospital.hospitalbackend.entity.Patient;
import com.hospital.hospitalbackend.service.PatientService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Add Patient
    @PostMapping
    public ResponseEntity<Patient> addPatient(
            @Valid @RequestBody PatientDTO patientDTO) {

        Patient savedPatient =
                patientService.addPatient(patientDTO);

        return new ResponseEntity<>(
                savedPatient,
                HttpStatus.CREATED);
    }

    // Get All Patients
    @GetMapping
    public List<Patient> getAllPatients() {

        return patientService.getAllPatients();
    }

    // Get Patient By ID
    @GetMapping("/{id}")
    public Patient getPatientById(
            @PathVariable int id) {

        return patientService.getPatientById(id);
    }

    // Update Patient
    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable int id,

            @Valid
            @RequestBody Patient updatedPatient) {

        return patientService
                .updatePatient(id, updatedPatient);
    }

    // Delete Patient
    @DeleteMapping("/{id}")
    public String deletePatient(
            @PathVariable int id) {

        return patientService.deletePatient(id);
    }

    // Pagination API
    @GetMapping("/pagination")
    public Page<Patient> getPatientsWithPagination(

            @RequestParam int page,

            @RequestParam int size) {

        return patientService
                .getPatientsWithPagination(page, size);
    }

    // Search Patients API
    @GetMapping("/search")
    public List<Patient> searchPatients(
            @RequestParam String name) {

        return patientService.searchPatients(name);
    }

    // Sort Patients API
    @GetMapping("/sort")
    public List<Patient> sortPatients(
            @RequestParam String field) {

        return patientService.sortPatients(field);
    }
}