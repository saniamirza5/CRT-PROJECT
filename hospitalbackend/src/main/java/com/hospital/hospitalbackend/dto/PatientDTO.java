package com.hospital.hospitalbackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import com.hospital.hospitalbackend.entity.Doctor;

public class PatientDTO {

    private int patientId;

    @NotBlank(message = "Patient name cannot be empty")
    private String patientName;

    @Positive(message = "Bed number must be positive")
    private int bedNumber;

    @Min(value = 0, message = "Days cannot be negative")
    private int days;

    private Doctor doctor;

    // Default Constructor
    public PatientDTO() {
    }

    // Parameterized Constructor
    public PatientDTO(int patientId,
                      String patientName,
                      int bedNumber,
                      int days,
                      Doctor doctor) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.bedNumber = bedNumber;
        this.days = days;
        this.doctor = doctor;
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

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}