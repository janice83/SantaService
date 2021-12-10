package com.indexzero.santaService.model;

import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

    @NotBlank(message = "Ei saa olla tyhjä")
    private String username;

    @NotBlank(message = "Ei saa olla tyhjä")
    @Size(min = 3, message = "Täytyy olla vähintään kolme merkkinen")
    private String password;
    private String userRole;

    /* Basic contact information */
    @NotBlank(message = "Ei saa olla tyhjä")
    private String firstName;
    @NotBlank(message = "Ei saa olla tyhjä")
    private String lastName;
    @NotBlank(message = "Ei saa olla tyhjä")
    private String email;
    @NotBlank(message = "Ei saa olla tyhjä")
    private String phoneNumber;
    @NotBlank(message = "Ei saa olla tyhjä")
    private String address;
    @NotBlank(message = "Ei saa olla tyhjä")
    private String postalCode;

    /* Profile */
    @ManyToOne
    private SantaProfile santaProfile;
    @ManyToOne
    private CustomerProfile customerProfile;

    public Boolean anyValueBlank() {
        if (this.firstName.isBlank()) return true;
        if (this.lastName.isBlank()) return true;
        if (this.email.isBlank()) return true;
        if (this.phoneNumber.isBlank()) return true;
        if (this.postalCode.isBlank()) return true;
        return false;
    }
    

}
