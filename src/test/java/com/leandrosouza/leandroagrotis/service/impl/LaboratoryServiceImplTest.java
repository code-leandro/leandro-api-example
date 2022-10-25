package com.leandrosouza.leandroagrotis.service.impl;

import com.leandrosouza.leandroagrotis.builder.FactoryLaboratoryTestUtil;
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
                .thenReturn(FactoryLaboratoryTestUtil.getList());
        List<Laboratory> list = service.findAll();
        assertThat(list).isNotNull().isNotEmpty();
        assertThat(list).hasSize(1);
        assertThat(list.get(0)).isNotNull();
        assertThat(list.get(0).getId()).isEqualTo(FactoryLaboratoryTestUtil.ID_TEST);
        assertThat(list.get(0).getName()).isEqualTo(FactoryLaboratoryTestUtil.NAME_TEST);
    }

    @Test
    void findById() {
        when(repository.findById(FactoryLaboratoryTestUtil.ID_TEST))
                .thenReturn(Optional.of(FactoryLaboratoryTestUtil.getLaboratory()));
        Laboratory property = service.findById(FactoryLaboratoryTestUtil.ID_TEST);
        assertThat(property).isNotNull();
        assertThat(property.getId()).isEqualTo(FactoryLaboratoryTestUtil.ID_TEST);
        assertThat(property.getName()).isEqualTo(FactoryLaboratoryTestUtil.NAME_TEST);
    }
    
    @Test
    void findByIdNull() {
        when(repository.findById(FactoryLaboratoryTestUtil.ID_TEST))
                .thenReturn(Optional.empty());
        Laboratory property = service.findById(FactoryLaboratoryTestUtil.ID_TEST);
        assertThat(property).isNull();
    }

    @Test
    void findByIdOrThrowsNotFoundException() {
        when(repository.findById(FactoryLaboratoryTestUtil.ID_TEST))
                .thenReturn(Optional.of(FactoryLaboratoryTestUtil.getLaboratory()));
        Laboratory property = service.findByIdOrThrowsNotFoundException(FactoryLaboratoryTestUtil.ID_TEST);
        assertThat(property).isNotNull();
        assertThat(property.getId()).isEqualTo(FactoryLaboratoryTestUtil.ID_TEST);
        assertThat(property.getName()).isEqualTo(FactoryLaboratoryTestUtil.NAME_TEST);
    }

    @Test
    void findByIdOrThrowsNotFoundException_throwsException() {
        when(repository.findById(FactoryLaboratoryTestUtil.ID_TEST))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
                () -> service.findByIdOrThrowsNotFoundException(FactoryLaboratoryTestUtil.ID_TEST)
        ).isInstanceOf(NotFoundException.class);
    }
}