package com.indexzero.santaService.services;

import java.util.List;
import java.util.Optional;
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

    /* @Autowired
    private SantaProfileRepository santaProfileRepository; */
    @Autowired
    private SantaProfileService santaProfileService;

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
        santaAccount.setSantaProfile(santaProfile);

        santaProfileService.saveSantaProfile(santaProfile);
        userAccountRepository.saveAndFlush(santaAccount);

    }

    @Transactional
    public void createCustomerAccount(UserAccount customerAccount) {
        customerAccount.setPassword(passwordEncoder.encode(customerAccount.getPassword()));
        customerAccount.setUsername(customerAccount.getFirstName());
        customerAccount.setUserRole("ROLE_CUSTOMER");
        CustomerProfile customerProfile = new CustomerProfile();
        customerAccount.setCustomerProfile(customerProfile);
        customerProfileRepository.save(customerProfile);
        userAccountRepository.saveAndFlush(customerAccount);
    }

    public Optional<UserAccount> findUserAccountById(Long id) {
        return userAccountRepository.findById(id);
    }

    public Optional<UserAccount> findUserAccountByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }

    /* Read */
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

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

    /* Update basic info: */
    @Transactional
    public boolean updateAccountInfo(Optional<UserAccount> oldAccount, UserAccount newAccount) {
        if (oldAccount.isPresent()) {
            /* Check if any value is blank */
            /* System.out.println(newAccount.); */
            /* System.out.println();
            System.out.println("Tyhjiä arvoja: "+newAccount.anyValueBlank());
            System.out.println();
            if (!newAccount.getFirstName().isBlank()) {
                oldAccount.get().setFirstName(newAccount.getFirstName());
            }
            if (!newAccount.getLastName().isBlank()) {
                oldAccount.get().setLastName(newAccount.getLastName());
            }
            if (!newAccount.getEmail().isBlank()) {
                oldAccount.get().setEmail(newAccount.getEmail());
            }
            if (!newAccount.getPhoneNumber().isBlank()) {
                oldAccount.get().setPhoneNumber(newAccount.getPhoneNumber());
            }
            if (!newAccount.getPostalCode().isBlank()) {
                oldAccount.get().setPostalCode(newAccount.getPostalCode());
            }
            return true; */
            if (newAccount.anyValueBlank()) {
                return false;
            }
            oldAccount.get().setFirstName(newAccount.getFirstName());
            oldAccount.get().setLastName(newAccount.getLastName());
            oldAccount.get().setEmail(newAccount.getEmail());
            oldAccount.get().setPhoneNumber(newAccount.getPhoneNumber());
            oldAccount.get().setPostalCode(newAccount.getPostalCode());
            return true;
        }
        return false;
        
    }
    /* Update username */
    @Transactional
    public void updateUsername(Optional<UserAccount> account, String username) {
        if (account.isPresent()) {
            account.get().setUsername(username);
            /* userAccountRepository.save(account.get()); */
        }
    }
    @Transactional
    public boolean deleteAccount(Optional<UserAccount> account) {
        if (account.isPresent()) {
            SantaProfile santaProfile = account.get().getSantaProfile();
            userAccountRepository.delete(account.get());
            santaProfileService.deleteSantaprofile(santaProfile);
            return true;
        }
        return false;
    }
    /* public boolean checkNull() throws IllegalAccessException {

    } */

    
}
