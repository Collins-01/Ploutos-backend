package com.collins.ploutos.ploutos.repository;

import com.collins.ploutos.ploutos.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {  // Changed UUID to Long

    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);


}