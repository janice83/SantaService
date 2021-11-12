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
@ContextConfiguration(classes = SantaController.class)
@WebMvcTest
public class SantaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetSantaPage() throws Exception {
        mockMvc.perform(get("/santa"))
            .andExpect(status().isOk());
    }
}
