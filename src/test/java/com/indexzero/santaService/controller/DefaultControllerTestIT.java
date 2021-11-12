package com.indexzero.santaService.controller;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DefaultController.class})
@WebMvcTest
public class DefaultControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomeOk() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk());
        
    }
}
