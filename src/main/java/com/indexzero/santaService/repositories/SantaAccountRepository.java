package com.indexzero.santaservice.repositories;

import java.util.Optional;

import com.indexzero.santaservice.model.SantaAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SantaAccountRepository extends JpaRepository<SantaAccount, Long> {
    
    Optional<SantaAccount> findByEmailEquals(String email); 

}
