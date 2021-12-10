package com.indexzero.santaService.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SantaProfile extends AbstractPersistable<Long> {
    
    private String santaProfileName;
    private String info;
    private int price;
    private boolean available;

    @OneToMany(mappedBy = "santaProfile")
    private List<UserAccount> users;
    
}
