package com.indexzero.santaService.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerAccount extends AbstractPersistable<Long>{
    private String firstName;
    private String lastName;
    
    private String email;
    private String password;

    private String address;

}
