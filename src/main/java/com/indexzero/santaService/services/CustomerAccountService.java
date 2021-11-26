package com.indexzero.santaService.services;

import java.util.Optional;

import com.indexzero.santaService.model.CustomerAccount;
import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.repositories.CustomerAccountRepository;
import com.indexzero.santaService.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Create */
    @Transactional
    public void createCustomerAccount(UserAccount customerAccount) {
        customerAccount.setPassword(passwordEncoder.encode(customerAccount.getPassword()));
        customerAccount.setUsername(customerAccount.getFirstName());
        customerAccount.setUserRole("ROLE_CUSTOMER");
        userAccountRepository.save(customerAccount);
    }
    /* Read */

    /* Update */

    /* Delete */
    
}
