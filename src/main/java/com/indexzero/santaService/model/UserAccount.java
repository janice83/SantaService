package com.indexzero.santaService.model;

import java.util.Objects;
import java.util.stream.Stream;

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
    /* Security */
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

    /* public boolean checkIfNull() {
        return Stream.of(
                firstName,
                lastName,
                email,
                phoneNumber,
                postalCode)
                .allMatch(Objects::isNull);
    } */
    public Boolean anyValueBlank() {
        if (this.firstName.isBlank()) return true;
        if (this.lastName.isBlank()) return true;
        if (this.email.isBlank()) return true;
        if (this.phoneNumber.isBlank()) return true;
        if (this.postalCode.isBlank()) return true;
        return false;
    }
    

}
