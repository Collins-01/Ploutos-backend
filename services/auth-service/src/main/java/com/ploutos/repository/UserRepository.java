package com.ploutos.repository;

import com.ploutos.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    // Placeholder for actual repository implementation (e.g., JpaRepository)

    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    public User save(User user) {
        return user;
    }
}
