package com.indexzero.santaService.repositories;

import com.indexzero.santaService.model.CustomerProfile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long>{
    
}
