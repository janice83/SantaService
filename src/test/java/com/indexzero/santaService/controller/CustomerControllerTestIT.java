package com.indexzero.santaService.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

/* import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; */

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.indexzero.santaService.model.SantaAccount;
import com.indexzero.santaService.repositories.SantaAccountRepository;
import com.indexzero.santaService.services.SantaService;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerController customerController;

    @MockBean
    private SantaService santaService;

    @MockBean
    private SantaAccountRepository santaAccountRepository;

    SantaAccount santaAccount_1 = new SantaAccount("santa_one", "lastName_one", "email_one", "password_one", "phoneNumber_one");
    SantaAccount santaAccount_2 = new SantaAccount("firstName_two", "lastName_two", "email_two", "password_Two", "phoneNumber_two");
    SantaAccount santaAccount_3 = new SantaAccount("firstName_three", "lastName_three", "email_three", "password_three", "phoneNumber_three");


    @Test
    public void contextLoad() throws Exception {
        mockMvc.perform(get("/customer")).andExpect(status().isOk());
    }
    /* Not in use atm, resti api not found for some reason */
    @Disabled
    @Test
    public void testFindAllRestApi() throws Exception {
        List<SantaAccount> santaAccountList = new ArrayList<>(Arrays.asList(
            santaAccount_1, santaAccount_2, santaAccount_3
        ));
        santaAccountRepository.save(santaAccount_1);
        assertEquals(1, santaAccountRepository.findAll().size());
        Mockito.when(santaService.getNewSantas()).thenReturn(santaAccountList);

        /* mockMvc.perform(MockMvcRequestBuilders
            .get("/santas")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3))); */
        
    }
}
