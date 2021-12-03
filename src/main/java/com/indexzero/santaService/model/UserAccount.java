package com.indexzero.santaService.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAccount extends AbstractPersistable<Long> {
    /* Security  */
    private String username;
    private String password;
    private String userRole;

    /* Basic contact information */
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String postalCode;

    /* Profile */
    @ManyToOne
    private SantaProfile santaProfile;
    @ManyToOne
    private CustomerProfile customerProfile;
    
}
