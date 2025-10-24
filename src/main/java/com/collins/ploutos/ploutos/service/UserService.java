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
        /// for test, let's not use token
        Optional<UserModel> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()) {
            if (passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
                Map<String, Object> response = new HashMap<>();
                response.put("user", user.orElseThrow(
                        () -> new RuntimeException("User not found with email: " + loginRequest.getEmail())));
                return response;
            }
        }
        throw new RuntimeException("Invalid email or password");
    }

    // In UserService.java, ensure the method is public
    public UserModel authenticate(String email, String password) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }

}
