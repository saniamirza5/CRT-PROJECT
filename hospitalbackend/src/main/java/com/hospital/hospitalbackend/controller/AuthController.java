package com.hospital.hospitalbackend.controller;

import com.hospital.hospitalbackend.auth.AuthRequest;
import com.hospital.hospitalbackend.jwt.JwtUtil;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(
            @RequestBody AuthRequest authRequest) {

        if (authRequest.getUsername().equals("admin")
                &&
                authRequest.getPassword().equals("admin123")) {

            return JwtUtil.generateToken(
                    authRequest.getUsername());
        }

        return "Invalid Username or Password";
    }
}