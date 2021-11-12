package com.indexzero.santaService.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(SantaController.class)
public class SantaControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetSantaPage() throws Exception {
        mockMvc.perform(get("/santa"))
            .andExpect(status().isOk());
    }
    @Test
    public void testAgain() {

    }
}
