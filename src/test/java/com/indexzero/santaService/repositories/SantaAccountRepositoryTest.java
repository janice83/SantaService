package com.indexzero.santaService.repositories;

import static org.assertj.core.api.Assertions.*;
import com.indexzero.santaService.model.SantaAccount;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

/* NOTE! repo should not be tested for existing api, only custom methods.
So this is just for an example. Remove these when adding own methods */

@ActiveProfiles("test")
@DataJpaTest
public class SantaAccountRepositoryTest {

    @Autowired
    private SantaAccountRepository santaAccountRepository;

    private SantaAccount santaAccount;

    @BeforeEach
    void setUp() {
        santaAccount = new SantaAccount();
        santaAccount.setFirstName("Pukki");
        santaAccount.setEmail("pukki@email.com");
        santaAccountRepository.save(santaAccount);
    }

    @AfterEach
    void tearDown() {
        santaAccountRepository.deleteAll();
    }

    @Test
    @DisplayName("Adding account to repo finds addes account and repo size should be 1")
    public void testAddSantaAccount() {
        // given
        // when
        SantaAccount result = santaAccountRepository.findAll().get(0);
        // then
        assertThat(result).isSameAs(result);
        assertThat(santaAccountRepository.findAll().size()).isEqualTo(1);
    }
    /* Include this test! */
    @Test
    public void testFindIfEmailExists() {
        String email = "pukki@email.com";
        String wrongEmail = "notpukki@gmail.com";
        SantaAccount result = santaAccountRepository.findByEmailEquals(email).get();
        
        assertThat(result).isSameAs(santaAccount);
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getEmail()).isNotEqualTo(wrongEmail);

    }
}
