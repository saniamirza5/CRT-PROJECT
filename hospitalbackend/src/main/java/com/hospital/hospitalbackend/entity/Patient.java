package com.hospital.hospitalbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")

public class Patient {

    @Id
    private int patientId;

    private String patientName;

    private String disease;

    private String doctorAssigned;

    // Default Constructor
    public Patient() {
    }

    // Constructor
    public Patient(
            int patientId,
            String patientName,
            String disease,
            String doctorAssigned) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.disease = disease;
        this.doctorAssigned = doctorAssigned;
    }

    // Getters and Setters

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctorAssigned() {
        return doctorAssigned;
    }

    public void setDoctorAssigned(String doctorAssigned) {
        this.doctorAssigned = doctorAssigned;
    }
}