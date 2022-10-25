package com.leandrosouza.leandroagrotis.api.controller;

import com.leandrosouza.leandroagrotis.api.payload.response.PersonResponse;
import com.leandrosouza.leandroagrotis.builder.FactoryPersonTestUtil;
import com.leandrosouza.leandroagrotis.exception.NotFoundException;
import com.leandrosouza.leandroagrotis.service.protocol.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonControllerTest {

    @InjectMocks
    PersonController controller;

    @Mock
    PersonService service;

    @Test
    void findById() {
        when(service.findById(FactoryPersonTestUtil.ID)).thenReturn(FactoryPersonTestUtil.getPersonWithId());
        ResponseEntity<PersonResponse> response = controller.findById(FactoryPersonTestUtil.ID);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isInstanceOf(PersonResponse.class);
        assertThat(response.getBody().getId()).isEqualTo(FactoryPersonTestUtil.ID);
    }

    @Test
    void findByIdNotFound() {
        when(service.findById(FactoryPersonTestUtil.ID)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> controller.findById(FactoryPersonTestUtil.ID));
    }

    @Test
    void save() {
        when(service.save(FactoryPersonTestUtil.getPersonRequestToModel()))
                .thenReturn(FactoryPersonTestUtil.getPersonWithId());
        ResponseEntity<PersonResponse> response = controller.save(FactoryPersonTestUtil.getPersonRequest());
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isInstanceOf(PersonResponse.class);
        assertThat(response.getBody().getId()).isEqualTo(FactoryPersonTestUtil.ID);
        assertThat(response.getBody().getName()).isEqualTo(FactoryPersonTestUtil.NAME);
    }

    @Test
    void findAll() {
        when(service.findAll()).thenReturn(FactoryPersonTestUtil.list());
        ResponseEntity<List<PersonResponse>> response = controller.findAll();
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody()).isInstanceOf(List.class);
        assertThat(response.getBody().get(0)).isInstanceOf(PersonResponse.class);
        assertThat(response.getBody().get(0).getId()).isEqualTo(FactoryPersonTestUtil.ID);
        assertThat(response.getBody().get(0).getName()).isEqualTo(FactoryPersonTestUtil.NAME);
    }

    @Test
    void update() {
        when(service.findById(FactoryPersonTestUtil.ID)).thenReturn(null);

        when(service.save(FactoryPersonTestUtil.getPersonRequestToModelWithId()))
                .thenReturn(FactoryPersonTestUtil.getPersonWithId());
        ResponseEntity<PersonResponse> response = controller.update(FactoryPersonTestUtil.ID, FactoryPersonTestUtil.getPersonRequest());
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isInstanceOf(PersonResponse.class);
        assertThat(response.getBody().getId()).isEqualTo(FactoryPersonTestUtil.ID);
        assertThat(response.getBody().getName()).isEqualTo(FactoryPersonTestUtil.NAME);
    }

    @Test
    void delete() {
        doNothing().when(service).delete(any());
        controller.delete(FactoryPersonTestUtil.ID);
        verify(service, times(1)).delete(FactoryPersonTestUtil.ID);
    }
}