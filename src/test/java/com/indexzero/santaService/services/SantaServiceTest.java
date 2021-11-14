package com.indexzero.santaService.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import com.indexzero.santaService.model.SantaAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@SpringBootTest
public class SantaServiceTest {

    @Autowired
    private SantaService santaService;

    private SantaAccount santaAccount;

    @BeforeEach
    public void init() {
        santaAccount = new SantaAccount();
        santaAccount.setFirstName("firstName");
        santaAccount.setLastName("lastName");
        santaAccount.setPassword("password");
        santaAccount.setEmail("email@mail");
        santaService.save(santaAccount);
    }

    @Test
    @DisplayName("Test should pass when an account is addes as SantaAccount")
    public void testFindAll() {
        assertEquals(santaAccount, santaService.getAllSantas().get(0));
        assertEquals(1, santaService.getAllSantas().size());
    }
    
    @Test
    @DisplayName("Test that new santa returns only firstname")
    public void testFindNewSantas() {
        assertEquals("firstName", santaService.getNewSantas().get(0).getFirstName());
        assertEquals(null, santaService.getNewSantas().get(0).getLastName());
        assertEquals(null, santaService.getNewSantas().get(0).getPhoneNumber());
        assertEquals(null, santaService.getNewSantas().get(0).getEmail());
        assertEquals(null, santaService.getNewSantas().get(0).getPassword());
    }
}
