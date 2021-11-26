package com.indexzero.santaService.repositories;

import com.indexzero.santaService.model.SantaProfile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SantaProfileRepository extends JpaRepository<SantaProfile, Long>{
    
}
