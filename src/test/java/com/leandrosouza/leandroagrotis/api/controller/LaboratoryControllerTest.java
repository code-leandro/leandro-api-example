package com.leandrosouza.leandroagrotis.api.controller;

import com.leandrosouza.leandroagrotis.api.payload.response.LaboratoryResponse;
import com.leandrosouza.leandroagrotis.api.payload.response.ListLaboratoryResponse;
import com.leandrosouza.leandroagrotis.builder.FactoryLaboratoryTestUtil;
import com.leandrosouza.leandroagrotis.service.protocol.LaboratoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class LaboratoryControllerTest {

    @InjectMocks
    LaboratoryController controller;

    @Mock
    LaboratoryService service;
    
    @Test
    void findAll() {
        when(service.findAll()).thenReturn(FactoryLaboratoryTestUtil.getList());
        ResponseEntity<ListLaboratoryResponse> response = controller.findAll();
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getLaboratories()).hasSize(1);
        assertThat(response.getBody()).isInstanceOf(ListLaboratoryResponse.class);
        assertThat(response.getBody().getLaboratories().get(0)).isInstanceOf(LaboratoryResponse.class);
        assertThat(response.getBody().getLaboratories().get(0).id()).isEqualTo(FactoryLaboratoryTestUtil.ID_TEST);
        assertThat(response.getBody().getLaboratories().get(0).name()).isEqualTo(FactoryLaboratoryTestUtil.NAME_TEST);
    }
}