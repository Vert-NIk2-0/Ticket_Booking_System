package com.practice.mybookingsystem.service.impl;

import com.practice.mybookingsystem.configuration.JwtUtil;
import com.practice.mybookingsystem.data.UserRepository;
import com.practice.mybookingsystem.data.entity.User;
import com.practice.mybookingsystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        Long userId = user.getId();
        String role = user.getRole().getRoleName();

        return jwtUtil.generateToken(username, userId, role);
    }
}
