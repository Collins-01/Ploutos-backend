package com.collins.ploutos.ploutos.repository;

import com.collins.ploutos.ploutos.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}
