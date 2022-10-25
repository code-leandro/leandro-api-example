package com.leandrosouza.leandroagrotis.service.impl;

import com.leandrosouza.leandroagrotis.domain.Person;
import com.leandrosouza.leandroagrotis.exception.NotFoundException;
import com.leandrosouza.leandroagrotis.persistence.PersonRepository;
import com.leandrosouza.leandroagrotis.service.protocol.LaboratoryService;
import com.leandrosouza.leandroagrotis.service.protocol.PersonService;
import com.leandrosouza.leandroagrotis.service.protocol.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
@Slf4j
class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final LaboratoryService laboratoryService;
    private final PropertyService propertyService;
    private final MessageSource messageSource;
    private final Locale locale;

    @Override
    public Person save(Person person) {
        log.info("[PersonService > save]: {}", person);
        validatePreSave(person);
        person = repository.save(person);
        posSave(person);
        return person;
    }

    public void validatePreSave(Person person) {
        if (person.getIdLaboratory() != null) {
            laboratoryService.findByIdOrThrowsNotFoundException(person.getIdLaboratory());
        }
        if (person.getIdProperty() != null) {
            propertyService.findByIdOrThrowsNotFoundException(person.getIdProperty());
        }
    }

    public void posSave(Person person) {
        person.setLaboratory(laboratoryService.findById(person.getIdLaboratory()));
        person.setProperty(propertyService.findById(person.getIdProperty()));
    }

    @Override
    public List<Person> findAll() {
        log.info("[PersonService > findAll]");
        return repository.findAll();
    }

    @Override
    public Person findById(Integer id) {
        Optional<Person> person = repository.findPerson(id);
        String messageNotFound = messageSource.getMessage("not_found.person", null, locale);
        return person.orElseThrow(() -> new NotFoundException(messageNotFound));
    }

    @Override
    public void delete(Integer id) {
        repository.delete(findById(id));
    }

}
