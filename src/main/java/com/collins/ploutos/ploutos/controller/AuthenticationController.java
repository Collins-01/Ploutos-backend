package com.collins.ploutos.ploutos.controller;

import com.collins.ploutos.ploutos.dto.request.LoginRequest;
import com.collins.ploutos.ploutos.dto.response.LoginResponse;
import com.collins.ploutos.ploutos.dto.request.RegisterRequest;
import com.collins.ploutos.ploutos.model.UserModel;
import com.collins.ploutos.ploutos.security.CustomUserDetailsService;
import com.collins.ploutos.ploutos.service.UserService;
import com.collins.ploutos.ploutos.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.collins.ploutos.ploutos.dto.response.UserResponse;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import com.collins.ploutos.ploutos.exceptions.UnAuthorizedException;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest registerRequest) {
        UserModel user = userService.register(registerRequest);
        UserResponse userResponse = UserResponse.fromUserModel(user);

        Map<String, Object> response = new HashMap<>();
        response.put("user", userResponse);
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserModel user = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            UserResponse userResponse = UserResponse.fromUserModel(user);

            // Generate JWT token if needed
            // String token = jwtUtil.generateToken(user);

            Map<String, Object> response = new HashMap<>();
            response.put("user", userResponse);
            response.put("message", "Login successful");
            // response.put("token", token); // Uncomment when JWT is implemented
            return ResponseEntity.ok(response);
        } catch (UnAuthorizedException e) {
            throw e;
        }
    }
}
