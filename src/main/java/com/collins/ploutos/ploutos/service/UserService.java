package com.collins.ploutos.ploutos.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import com.collins.ploutos.ploutos.model.UserModel;
import com.collins.ploutos.enums.UserRole;
import com.collins.ploutos.ploutos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.collins.ploutos.ploutos.util.JwtUtil;

import com.collins.ploutos.ploutos.dto.request.LoginRequest;
import com.collins.ploutos.ploutos.dto.request.RegisterRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.collins.ploutos.ploutos.exceptions.UnAuthorizedException;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired // Inject the UserRepositoryß dependency
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    // Create
    public UserModel register(RegisterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Email and password are required");
        }

        UserModel user = UserModel.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(UserRole.USER)
                .active(true)
                .build();

        return userRepository.save(user);
    }

    public Map<String, Object> login(LoginRequest loginRequest) {
        try {
            // This will throw UnauthorizedException if authentication fails
            UserModel user = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("message", "Login successful");
            return response;
        } catch (UnAuthorizedException e) {
            // Re-throw to be handled by the global exception handler
            throw e;
        }
    }

    public UserModel authenticate(String email, String password) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnAuthorizedException("User not found with email: " + email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnAuthorizedException("Invalid email or password");
        }

        return user;
    }

}
