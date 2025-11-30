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

    public String register(RegisterRequest request) {
        // 1. Check if email exists
        // 2. If email exist, send email already registered error.
        // 3. Assuming the user has no account registered, hash the password
        // 4. Create a new user and save it to the redis cache
        // 5. Cache will be cleared after 24 hours
        return "User registered successfully";
    }

    public String requestOtp(RequestOtp request) {
        // 1. Check if email exists in the cache
        // 2. if it does not, throw error for email not registered.
        // 3. If the email exists, generate a code, store it in the cache and send it to
        // the user's email.
        //
        return "OTP sent successfully";
    }

    public String verifyOtp(VerifyOtp request) {
        // 1. Check if we have the email in the cache.
        // 2. Check if the code is valid.
        // 3. If the code is valid, create a new user and save it to the database.
        // 4. Generate a token and return it.
        // 5. send a successfully registration email to the user.
        // 6. Clear the cache.
        return "OTP verified successfully";
    }

}
