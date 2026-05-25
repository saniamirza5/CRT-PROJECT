package com.hospital.hospitalbackend.service;

import com.hospital.hospitalbackend.entity.Doctor;
import com.hospital.hospitalbackend.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Add Doctor
    public Doctor addDoctor(Doctor doctor) {

        return doctorRepository.save(doctor);
    }

    // Get All Doctors
    public List<Doctor> getAllDoctors() {

        return doctorRepository.findAll();
    }
}