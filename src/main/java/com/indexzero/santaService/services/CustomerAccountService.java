package com.indexzero.santaService.services;

import java.util.Optional;

import com.indexzero.santaService.model.CustomerAccount;
import com.indexzero.santaService.repositories.CustomerAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerAccountService {

    @Autowired
    private CustomerAccountRepository cAccountRepository;

    public void createCustomerAccount(CustomerAccount customerAccount) {
        Optional<CustomerAccount> accountToAdd = cAccountRepository.findByEmailEquals(customerAccount.getEmail());
        if (accountToAdd.isPresent()) {
            throw new IllegalArgumentException("Email taken!");
        }
        accountToAdd.get().setUserRole("CUSTOMER_ROLE");
    }
    
}
