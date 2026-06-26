package com.divyanka.campus_edge.service;

import com.divyanka.campus_edge.dto.*;
import com.divyanka.campus_edge.entity.*;
import com.divyanka.campus_edge.exception.BadRequestException;
import com.divyanka.campus_edge.repository.UserRepository;
import com.divyanka.campus_edge.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${app.allowed-email-domain}")
    private String allowedDomain;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest req) {
        String domain = req.getEmail().substring(req.getEmail().indexOf("@") + 1);
        if (!domain.equalsIgnoreCase(allowedDomain)) {
            throw new BadRequestException("Only " + allowedDomain + " emails are allowed to register");
        }

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("An account with this email already exists");
        }

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setBatchYear(req.getBatchYear());
        user.setBranch(req.getBranch());
        user.setRole(Role.STUDENT); // default, seniors upgraded later via admin or graduation check
        user.setIsEmailVerified(false); // flip true after OTP step (Phase 1.5)

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, user.getName(), user.getEmail(), user.getRole().name());
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, user.getName(), user.getEmail(), user.getRole().name());
    }
}