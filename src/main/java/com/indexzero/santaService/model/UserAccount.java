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
public class UserAccount extends AbstractPersistable<Long> {

    private String username;
    private String password;
    private String userRole;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String presentation;
    private String phoneNumber;
    private String postalCode;
    
}
