package com.indexzero.santaService.repositories;

import static org.assertj.core.api.Assertions.*;

import com.indexzero.santaService.model.SantaAccount;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.var;

@ActiveProfiles("test")
@DataJpaTest
public class SantaAccountRepositoryTest {

    @Autowired
    private SantaAccountRepository santaAccountRepository;

    @Test
    @DisplayName("Adding account to repo works")
    public void testAddSantaAccount() {
        // given
        assertThat(santaAccountRepository.findAll().size()).isEqualTo(0);
        SantaAccount account = new SantaAccount();
        account.setFirstName("firstName");
        // when
        santaAccountRepository.save(account);
        var result = santaAccountRepository.findAll().get(0);
        // then
        assertThat(result).isEqualTo(account);
        assertThat(santaAccountRepository.findAll().size()).isEqualTo(1);
    }

    @Disabled
    public void testFindByFirstName() {
        SantaAccount account = new SantaAccount();
        account.setFirstName("firstName");
        
    }
}
