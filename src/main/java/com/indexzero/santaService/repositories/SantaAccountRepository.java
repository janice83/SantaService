package com.indexzero.santaService.repositories;

import com.indexzero.santaService.model.SantaAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SantaAccountRepository extends JpaRepository<SantaAccount, Long> {

}
