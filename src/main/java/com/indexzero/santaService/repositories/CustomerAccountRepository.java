package com.indexzero.santaService.repositories;

import com.indexzero.santaService.model.CustomerAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

}
