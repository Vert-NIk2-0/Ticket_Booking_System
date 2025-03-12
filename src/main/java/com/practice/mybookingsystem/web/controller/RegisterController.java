package com.practice.mybookingsystem.web.controller;

import com.practice.mybookingsystem.data.entity.User;
import com.practice.mybookingsystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

        userServiceImpl.registerUser(user);
        return ResponseEntity.status(201).body("User registered successfully");
    }
}
