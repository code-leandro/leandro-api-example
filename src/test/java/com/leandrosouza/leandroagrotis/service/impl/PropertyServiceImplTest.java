package com.leandrosouza.leandroagrotis.service.impl;

import com.leandrosouza.leandroagrotis.builder.PropertyBuilder;
import com.leandrosouza.leandroagrotis.domain.Property;
import com.leandrosouza.leandroagrotis.exception.NotFoundException;
import com.leandrosouza.leandroagrotis.persistence.PropertyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropertyServiceImplTest {

    @InjectMocks
    PropertyServiceImpl service;

    @Mock
    PropertyRepository repository;

    @Mock
    MessageSource messageSource;

    @Test
    void findAll() {
        when(repository.findAll())
                .thenReturn(PropertyBuilder.getList());
        List<Property> list = service.findAll();
        assertThat(list).isNotNull().isNotEmpty();
        assertThat(list).hasSize(1);
        assertThat(list.get(0)).isNotNull();
        assertThat(list.get(0).getId()).isEqualTo(PropertyBuilder.ID_TEST);
        assertThat(list.get(0).getName()).isEqualTo(PropertyBuilder.NAME_TEST);
    }

    @Test
    void findById() {
        when(repository.findById(PropertyBuilder.ID_TEST))
                .thenReturn(Optional.of(PropertyBuilder.getProperty()));
        Property property = service.findById(PropertyBuilder.ID_TEST);
        assertThat(property).isNotNull();
        assertThat(property.getId()).isEqualTo(PropertyBuilder.ID_TEST);
        assertThat(property.getName()).isEqualTo(PropertyBuilder.NAME_TEST);
    }

    @Test
    void findByIdNull() {
        when(repository.findById(PropertyBuilder.ID_TEST))
                .thenReturn(Optional.empty());
        Property property = service.findById(PropertyBuilder.ID_TEST);
        assertThat(property).isNull();
    }

    @Test
    void findByIdOrThrowsNotFoundException() {
        when(repository.findById(PropertyBuilder.ID_TEST))
                .thenReturn(Optional.of(PropertyBuilder.getProperty()));
        Property property = service.findByIdOrThrowsNotFoundException(PropertyBuilder.ID_TEST);
        assertThat(property).isNotNull();
        assertThat(property.getId()).isEqualTo(PropertyBuilder.ID_TEST);
        assertThat(property.getName()).isEqualTo(PropertyBuilder.NAME_TEST);
    }

    @Test
    void findByIdOrThrowsNotFoundException_throwsException() {
        when(repository.findById(PropertyBuilder.ID_TEST))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
                () -> service.findByIdOrThrowsNotFoundException(PropertyBuilder.ID_TEST)
        ).isInstanceOf(NotFoundException.class);
    }
}