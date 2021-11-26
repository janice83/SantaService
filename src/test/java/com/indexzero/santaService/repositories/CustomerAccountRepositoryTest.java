package com.indexzero.santaService.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

/* NOTE! repo should not be tested for existing api, only custom methods.
So this is just for an example. Remove these when adding own methods */

@ActiveProfiles("testi")
@DataJpaTest
public class CustomerAccountRepositoryTest {

    /* @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Test
    public void testAccountRepoSavesInstance() {
        assertThat(customerAccountRepository.findAll().size()).isZero();
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setFirstName("firstName");
        customerAccountRepository.save(customerAccount);
        assertThat(customerAccountRepository.findAll().get(0)).isSameAs(customerAccount);
        assertThat(customerAccountRepository.findAll().size()).isEqualTo(1);

    } */
}
