package com.leandrosouza.leandroagrotis.api.controller;

import com.leandrosouza.leandroagrotis.api.payload.response.ListPropertyResponse;
import com.leandrosouza.leandroagrotis.api.payload.response.PropertyResponse;
import com.leandrosouza.leandroagrotis.builder.FactoryPropertyTestUtil;
import com.leandrosouza.leandroagrotis.service.protocol.PropertyService;
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
class PropertyControllerTest {

    @InjectMocks
    PropertyController controller;

    @Mock
    PropertyService service;
    
    @Test
    void findAll() {
        when(service.findAll()).thenReturn(FactoryPropertyTestUtil.getList());
        ResponseEntity<ListPropertyResponse> response = controller.findAll();
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getProperties()).hasSize(1);
        assertThat(response.getBody()).isInstanceOf(ListPropertyResponse.class);
        assertThat(response.getBody().getProperties()).isInstanceOf(List.class);
        assertThat(response.getBody().getProperties().get(0)).isInstanceOf(PropertyResponse.class);
        assertThat(response.getBody().getProperties().get(0).id()).isEqualTo(FactoryPropertyTestUtil.ID_TEST);
        assertThat(response.getBody().getProperties().get(0).name()).isEqualTo(FactoryPropertyTestUtil.NAME_TEST);
    }
}