package com.indexzero.santaService.repositories;

import java.util.Optional;

import com.indexzero.santaService.model.SantaAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SantaAccountRepository extends JpaRepository<SantaAccount, Long> {
    
    Optional<SantaAccount> findByEmailEquals(String email);

    Optional<SantaAccount> findByUsername(String username); 
    

}
