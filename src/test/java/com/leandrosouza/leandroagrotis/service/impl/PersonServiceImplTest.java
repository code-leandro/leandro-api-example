package com.leandrosouza.leandroagrotis.service.impl;

import com.leandrosouza.leandroagrotis.builder.FactoryLaboratoryTestUtil;
import com.leandrosouza.leandroagrotis.builder.FactoryPersonTestUtil;
import com.leandrosouza.leandroagrotis.builder.FactoryPropertyTestUtil;
import com.leandrosouza.leandroagrotis.domain.Person;
import com.leandrosouza.leandroagrotis.exception.NotFoundException;
import com.leandrosouza.leandroagrotis.persistence.PersonRepository;
import com.leandrosouza.leandroagrotis.service.protocol.LaboratoryService;
import com.leandrosouza.leandroagrotis.service.protocol.PropertyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceImplTest {

    @InjectMocks
    PersonServiceImpl service;

    @Mock
    PersonRepository repository;

    @Mock
    LaboratoryService laboratoryService;

    @Mock
    PropertyService propertyService;

    @Mock
    MessageSource messageSource;

    @Test
    void save() {
        Person personToInclude = FactoryPersonTestUtil.getPerson();
        Person personSaved = FactoryPersonTestUtil.getPersonWithId();
        when(repository.save(personToInclude)).thenReturn(personSaved);

        when(laboratoryService.findByIdOrThrowsNotFoundException(FactoryLaboratoryTestUtil.ID_TEST))
                .thenReturn(FactoryLaboratoryTestUtil.getLaboratory());
        when(propertyService.findByIdOrThrowsNotFoundException(FactoryPropertyTestUtil.ID_TEST))
                .thenReturn(FactoryPropertyTestUtil.getProperty());

        Person result = service.save(personToInclude);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getId()).isEqualTo(FactoryPersonTestUtil.ID);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(FactoryPersonTestUtil.list());
        List<Person> list = service.findAll();
        assertThat(list).isNotNull().isNotEmpty();
        assertThat(list).hasSize(1);
        assertThat(list.get(0)).isNotNull();
        assertThat(list.get(0).getId()).isEqualTo(FactoryPersonTestUtil.ID);
    }

    @Test
    void findById() {
        Person personWithId = FactoryPersonTestUtil.getPersonWithId();
        when(repository.findPerson(FactoryPersonTestUtil.ID))
                .thenReturn(Optional.of(personWithId));

        Person person = service.findById(FactoryPersonTestUtil.ID);
        assertThat(person).isNotNull();
        assertThat(person).isInstanceOf(Person.class);
        assertThat(person.getId()).isEqualTo(FactoryPersonTestUtil.ID);
        assertThat(person.getName()).isEqualTo(FactoryPersonTestUtil.NAME);
    }

    @Test
    void findByIdNull() {
        when(repository.findPerson(FactoryPersonTestUtil.ID))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.findById(FactoryPersonTestUtil.ID));
    }

    @Test
    void delete() {
        Person personWithId = FactoryPersonTestUtil.getPersonWithId();
        when(repository.findPerson(FactoryPersonTestUtil.ID)).thenReturn(Optional.of(personWithId));
        doNothing().when(repository).delete(personWithId);
        service.delete(FactoryPersonTestUtil.ID);
        verify(repository, times(1)).delete(personWithId);
    }
}