package com.leandrosouza.leandroagrotis.it;

import com.leandrosouza.leandroagrotis.service.protocol.PropertyService;
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
public class PropertyControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PropertyService service;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/v1/property"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.properties").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.properties[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.properties[0].id").isNumber());
    }
}
