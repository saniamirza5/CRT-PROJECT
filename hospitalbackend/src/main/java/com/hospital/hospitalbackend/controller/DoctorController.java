package com.hospital.hospitalbackend.controller;

import com.hospital.hospitalbackend.entity.Doctor;
import com.hospital.hospitalbackend.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Add Doctor
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {

        return doctorService.addDoctor(doctor);
    }

    // Get All Doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {

        return doctorService.getAllDoctors();
    }
}