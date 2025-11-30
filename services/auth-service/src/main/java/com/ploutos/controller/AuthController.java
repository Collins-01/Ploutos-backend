package com.ploutos.controller;

import com.ploutos.dto.AuthResponse;
import com.ploutos.dto.LoginRequest;
import com.ploutos.dto.RegisterRequest;
import com.ploutos.dto.RequestOtp;
import com.ploutos.dto.VerifyOtp;
import com.ploutos.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/request-otp")
    public ResponseEntity<AuthResponse> requestOtp(@RequestBody RequestOtp email) {
        return ResponseEntity.ok(authService.requestOtp(email));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestBody VerifyOtp email) {
        return ResponseEntity.ok(authService.verifyOtp(email));
    }
}
