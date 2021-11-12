package com.indexzero.santaService.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import com.indexzero.santaService.model.SantaAccount;
import com.indexzero.santaService.repositories.SantaAccountRepository;
import com.indexzero.santaService.services.SantaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class SantaServiceTest {

    @Autowired
    private SantaService santaService;

    @Test
    public void testCreateSantaAccount() {
        assertTrue(dbSizeIsCorrect(0));
        SantaAccount account = new SantaAccount();
        account.setFirstName("firstName");
        account.setLastName("lastName");
        account.setPassword("password");
        account.setEmail("email@mail");
        santaService.save(account);
        assertEquals(account, santaService.getAllSantas().get(0));
        assertTrue(dbSizeIsCorrect(1));
    }
    public boolean dbSizeIsCorrect(int size) {
        return size == santaService.getAllSantas().size();
    }
    @Test
    public void testTestin() {
        
    }
}
