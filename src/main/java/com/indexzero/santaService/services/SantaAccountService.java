package com.indexzero.santaService.services;

import java.util.List;
import java.util.stream.Collectors;

import com.indexzero.santaService.model.SantaAccount;
import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.repositories.SantaAccountRepository;
import com.indexzero.santaService.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
public class SantaAccountService {


    @Autowired
    private UserAccountRepository userAccountRepository;

    /* @Autowired
    private PasswordEncoder passwordEncoder; */

    /* Save account */
    @Transactional
    public void save(UserAccount userAccount) {
        //userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setUsername(userAccount.getFirstName());
        userAccount.setUserRole("ROLE_SANTA");
        userAccountRepository.save(userAccount);

    }

    /* Get all santas: */
    public List<UserAccount> getAllSantas() {
        /* return userAccountRepository.findAll().stream()
            .filter(a -> a.getUserRole().equals("ROLE_SANTA"))
            .collect(Collectors.toList()); */
            return userAccountRepository.findAll();
    }

    /* Include only what needed */
    public List<UserAccount> getNewSantas() {
        return userAccountRepository.findAll().stream()
            //.filter(user -> user.getUserRole().equals("ROLE_SANTA"))
            .map(account -> {
            UserAccount newAccount = new UserAccount();
            newAccount.setFirstName(account.getFirstName());
            return newAccount;
        }).collect(Collectors.toList());
    }

}
