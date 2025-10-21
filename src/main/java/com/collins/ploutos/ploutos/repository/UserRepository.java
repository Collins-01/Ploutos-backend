
package com.collins.ploutos.ploutos.repository;

import com.collins.ploutos.ploutos.model.UserModel;
import com.collins.ploutos.ploutos.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    // These methods will be automatically implemented by Spring Data JPA
    @Query("SELECT u FROM UserModel u WHERE u.username = :username")
    Optional<UserModel> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM UserModel u WHERE u.email = :email")
    Optional<UserModel> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM UserModel u WHERE u.role = :role")
    Optional<UserModel> findByRole(@Param("role") UserRole role);

}
