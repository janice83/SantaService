package com.indexzero.santaService.repositories;

import java.util.Optional;

import com.indexzero.santaService.model.CustomerAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    Optional<CustomerAccount> findByEmailEquals(String email);

    Optional<CustomerAccount> findByUsername(String username);
}
