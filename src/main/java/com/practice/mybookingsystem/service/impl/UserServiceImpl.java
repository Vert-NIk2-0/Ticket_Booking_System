package com.practice.mybookingsystem.service.impl;

import com.practice.mybookingsystem.data.RoleRepository;
import com.practice.mybookingsystem.data.UserRepository;
import com.practice.mybookingsystem.data.entity.Role;
import com.practice.mybookingsystem.data.entity.User;
import com.practice.mybookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        Role userRole = roleRepository.findById(1)
                .orElseThrow(() -> new IllegalArgumentException("Role USER not found"));
        user.setRole(userRole);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
