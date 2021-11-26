package com.indexzero.santaService.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.indexzero.santaService.model.CustomerProfile;
import com.indexzero.santaService.model.SantaProfile;
import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.repositories.CustomerProfileRepository;
import com.indexzero.santaService.repositories.SantaProfileRepository;
import com.indexzero.santaService.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private SantaProfileRepository santaProfileRepository;

    @Autowired
    private CustomerProfileRepository customerProfileRepository;
    
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /* Create */
    @Transactional
    public void saveSantaAccount(UserAccount santaAccount) {
        santaAccount.setPassword(passwordEncoder.encode(santaAccount.getPassword()));
        santaAccount.setUsername(santaAccount.getFirstName());
        santaAccount.setUserRole("ROLE_SANTA");
        SantaProfile santaProfile = new SantaProfile();
        
        santaProfileRepository.save(santaProfile);
        santaAccount.setSantaProfile(santaProfile);
        userAccountRepository.saveAndFlush(santaAccount);

    }
    @Transactional
    public void createCustomerAccount(UserAccount customerAccount) {
        customerAccount.setPassword(passwordEncoder.encode(customerAccount.getPassword()));
        customerAccount.setUsername(customerAccount.getFirstName());
        customerAccount.setUserRole("ROLE_CUSTOMER");
        CustomerProfile customerProfile = new CustomerProfile();

        customerProfileRepository.save(customerProfile);
        customerAccount.setCustomerProfile(customerProfile);
        userAccountRepository.saveAndFlush(customerAccount);
    }

    /* Read */
    /* Get Santas by role, and include only what needed */
    public List<UserAccount> getNewSantas() {
        return userAccountRepository.findAll().stream()
            .filter(user -> user.getUserRole().equals("ROLE_SANTA"))
            .map(account -> {
            UserAccount newAccount = new UserAccount();
            newAccount.setFirstName(account.getFirstName());
            return newAccount;
        }).collect(Collectors.toList());
    }
    /* Get all santas, by role: */
    public List<UserAccount> getAllSantaUsers() {
        return userAccountRepository.findAll().stream()
            .filter(user -> user.getUserRole().equals("ROLE_SANTA"))
            .collect(Collectors.toList());
    }

    /* Update */

    /* Delete */
}
