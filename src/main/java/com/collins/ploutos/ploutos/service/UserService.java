package com.collins.ploutos.ploutos.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.collins.ploutos.ploutos.model.UserModel;
import com.collins.ploutos.enums.UserRole;
import com.collins.ploutos.ploutos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired // Inject the UserRepositoryß dependency
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create
    public UserModel createUser(UserModel user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (StringUtils.hasText(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new IllegalArgumentException("Password is required");
        }

        return userRepository.save(user);
    }

    // Read
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserModel> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserModel> getUsersByRole(UserRole role) {
        return userRepository.findByRole(role)
                .map(List::of)
                .orElse(List.of());
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // Update
    public UserModel updateUser(UserModel user) {
        // Verify user exists
        Long id = user.getId();
        if (id == null || !userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        return userRepository.save(user);
    }

    // Delete
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    // Additional business methods
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
