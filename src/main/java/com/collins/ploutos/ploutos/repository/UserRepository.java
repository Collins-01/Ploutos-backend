package com.collins.ploutos.ploutos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.collins.ploutos.ploutos.model.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Long> {
    /// checks if this email exist
    boolean existsByEmail(String email);
}
