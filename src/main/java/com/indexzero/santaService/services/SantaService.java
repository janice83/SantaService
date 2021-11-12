package com.indexzero.santaService.services;

import java.util.List;
import java.util.stream.Collectors;

import com.indexzero.santaService.model.SantaAccount;
import com.indexzero.santaService.repositories.SantaAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SantaService {

    @Autowired
    private SantaAccountRepository santaAccountRepository;

    public void save(SantaAccount santaAccount) {
        santaAccountRepository.save(santaAccount);

    }

    /* Get all santas: */
    public List<SantaAccount> getAllSantas() {
        return santaAccountRepository.findAll();
    }

    /* Include only what needed */
    public List<SantaAccount> getNewSantas() {
        return santaAccountRepository.findAll().stream().map(account -> {
            SantaAccount newAccount = new SantaAccount();
            newAccount.setFirstName(account.getFirstName());
            return newAccount;
        }).collect(Collectors.toList());
    }

}
