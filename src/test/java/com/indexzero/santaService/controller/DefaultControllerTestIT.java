package com.indexzero.santaService.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.indexzero.santaService.controller.DefaultController;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(DefaultController.class)
public class DefaultControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomeOk() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk());
        
    }
    @Test
    public void testiForTesting() {

    }
}
