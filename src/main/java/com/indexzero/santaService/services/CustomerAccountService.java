package com.indexzero.santaService.services;

import java.util.Optional;

import com.indexzero.santaService.model.CustomerAccount;
import com.indexzero.santaService.repositories.CustomerAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerAccountService {

    @Autowired
    private CustomerAccountRepository cAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createCustomerAccount(CustomerAccount customerAccount) {
        customerAccount.setPassword(passwordEncoder.encode(customerAccount.getPassword()));
        customerAccount.setUsername(customerAccount.getFirstName());
        customerAccount.setUserRole("ROLE_CUSTOMER");
        cAccountRepository.save(customerAccount);
    }
    
}
