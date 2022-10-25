package com.leandrosouza.leandroagrotis.service.impl;

import com.leandrosouza.leandroagrotis.builder.LaboratoryBuilder;
import com.leandrosouza.leandroagrotis.builder.LaboratoryBuilder;
import com.leandrosouza.leandroagrotis.builder.LaboratoryBuilder;
import com.leandrosouza.leandroagrotis.domain.Laboratory;
import com.leandrosouza.leandroagrotis.domain.Laboratory;
import com.leandrosouza.leandroagrotis.domain.Laboratory;
import com.leandrosouza.leandroagrotis.exception.NotFoundException;
import com.leandrosouza.leandroagrotis.persistence.LaboratoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LaboratoryServiceImplTest {

    @InjectMocks
    LaboratoryServiceImpl service;

    @Mock
    LaboratoryRepository repository;

    @Mock
    MessageSource messageSource;
    
    @Test
    void findAll() {
        when(repository.findAll())
                .thenReturn(LaboratoryBuilder.getList());
        List<Laboratory> list = service.findAll();
        assertThat(list).isNotNull().isNotEmpty();
        assertThat(list).hasSize(1);
        assertThat(list.get(0)).isNotNull();
        assertThat(list.get(0).getId()).isEqualTo(LaboratoryBuilder.ID_TEST);
        assertThat(list.get(0).getName()).isEqualTo(LaboratoryBuilder.NAME_TEST);
    }

    @Test
    void findById() {
        when(repository.findById(LaboratoryBuilder.ID_TEST))
                .thenReturn(Optional.of(LaboratoryBuilder.getLaboratory()));
        Laboratory property = service.findById(LaboratoryBuilder.ID_TEST);
        assertThat(property).isNotNull();
        assertThat(property.getId()).isEqualTo(LaboratoryBuilder.ID_TEST);
        assertThat(property.getName()).isEqualTo(LaboratoryBuilder.NAME_TEST);
    }
    
    @Test
    void findByIdNull() {
        when(repository.findById(LaboratoryBuilder.ID_TEST))
                .thenReturn(Optional.empty());
        Laboratory property = service.findById(LaboratoryBuilder.ID_TEST);
        assertThat(property).isNull();
    }

    @Test
    void findByIdOrThrowsNotFoundException() {
        when(repository.findById(LaboratoryBuilder.ID_TEST))
                .thenReturn(Optional.of(LaboratoryBuilder.getLaboratory()));
        Laboratory property = service.findByIdOrThrowsNotFoundException(LaboratoryBuilder.ID_TEST);
        assertThat(property).isNotNull();
        assertThat(property.getId()).isEqualTo(LaboratoryBuilder.ID_TEST);
        assertThat(property.getName()).isEqualTo(LaboratoryBuilder.NAME_TEST);
    }

    @Test
    void findByIdOrThrowsNotFoundException_throwsException() {
        when(repository.findById(LaboratoryBuilder.ID_TEST))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
                () -> service.findByIdOrThrowsNotFoundException(LaboratoryBuilder.ID_TEST)
        ).isInstanceOf(NotFoundException.class);
    }
}