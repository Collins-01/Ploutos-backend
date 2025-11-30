package com.ploutos.service;

import com.ploutos.dto.AuthResponse;
import com.ploutos.dto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request) {
        // Placeholder for authentication logic
        String token = jwtService.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}
