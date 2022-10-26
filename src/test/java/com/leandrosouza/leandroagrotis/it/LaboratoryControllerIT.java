package com.leandrosouza.leandroagrotis.it;

import com.leandrosouza.leandroagrotis.service.protocol.LaboratoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LaboratoryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    LaboratoryService service;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/v1/laboratory"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.laboratories").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.laboratories[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.laboratories[0].id").isNumber());
    }
}
