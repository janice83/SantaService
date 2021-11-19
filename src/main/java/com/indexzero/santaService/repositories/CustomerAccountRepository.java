package com.indexzero.santaservice.repositories;

import com.indexzero.santaservice.model.CustomerAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

}
