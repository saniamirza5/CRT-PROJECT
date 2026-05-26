package com.hospital.hospitalbackend.service;

import com.hospital.hospitalbackend.entity.Patient;
import com.hospital.hospitalbackend.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospitalbackend.dto.PatientDTO;
import com.hospital.hospitalbackend.customexception.PatientNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Logger
    private static final Logger logger =
            LoggerFactory.getLogger(PatientService.class);

    // Add Patient
    public Patient addPatient(PatientDTO patientDTO) {

        logger.info("Adding patient: {}",
                patientDTO.getPatientName());

        Patient patient = new Patient();

        patient.setPatientId(patientDTO.getPatientId());
        patient.setPatientName(patientDTO.getPatientName());
        patient.setDisease(patientDTO.getDisease());
        patient.setDoctorAssigned(patientDTO.getDoctorAssigned());

        return patientRepository.save(patient);
    }

    // Get All Patients
    public List<Patient> getAllPatients() {

        logger.info("Fetching all patients");

        return patientRepository.findAll();
    }

    // Get Patient By ID
    public Patient getPatientById(int id) {

        logger.info("Fetching patient with ID: {}", id);

        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient with ID " + id + " not found"));
    }

    // Update Patient
    public Patient updatePatient(int id, Patient updatedPatient) {

        logger.info("Updating patient with ID: {}", id);

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient with ID " + id + " not found"));

        patient.setPatientName(updatedPatient.getPatientName());
        patient.setDisease(updatedPatient.getDisease());
        patient.setDoctorAssigned(updatedPatient.getDoctorAssigned());

        return patientRepository.save(patient);
    }

    // Delete Patient
    public String deletePatient(int id) {

        logger.info("Deleting patient with ID: {}", id);

        patientRepository.deleteById(id);

        return "Patient Discharged Successfully";
    }

    // Pagination
    public Page<Patient> getPatientsWithPagination(
            int page,
            int size) {

        logger.info("Fetching patients with pagination");

        Pageable pageable = PageRequest.of(page, size);

        return patientRepository.findAll(pageable);
    }

    // Search Patients
    public List<Patient> searchPatients(String name) {

        logger.info("Searching patients with name: {}", name);

        return patientRepository
                .findByPatientNameContaining(name);
    }

    // Sort Patients
    public List<Patient> sortPatients(String field) {

        logger.info("Sorting patients by field: {}", field);

        return patientRepository.findAll(
                Sort.by(Sort.Direction.ASC, field));
    }
}