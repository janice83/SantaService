package com.indexzero.santaService.repositories;

import java.util.Optional;

import com.indexzero.santaService.model.UserAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    Optional<UserAccount> findByEmail(String email);

    Optional<UserAccount> findByUsername(String username);
}
