package com.hospital.hospitalbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")

public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int doctorId;

    private String doctorName;

    private String specialization;

    // Default Constructor
    public Doctor() {
    }

    // Getters and Setters

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}