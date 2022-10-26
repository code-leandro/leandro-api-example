package com.leandrosouza.leandroagrotis.service.impl;

import com.leandrosouza.leandroagrotis.domain.Laboratory;
import com.leandrosouza.leandroagrotis.domain.Person;
import com.leandrosouza.leandroagrotis.domain.Property;
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
        return person;
    }

    private void validatePreSave(Person person) {
        log.info("[PersonService > validatePreSave] {}", person);
        if (person.getIdLaboratory() != null) {
            Laboratory laboratory = laboratoryService.findByIdOrThrowsNotFoundException(person.getIdLaboratory());
            person.setLaboratory(laboratory);
        }
        if (person.getIdProperty() != null) {
            Property property = propertyService.findByIdOrThrowsNotFoundException(person.getIdProperty());
            person.setProperty(property);
        }
    }

    @Override
    public List<Person> findAll() {
        log.info("[PersonService > findAll]");
        return repository.findAll();
    }

    @Override
    public Person findById(Integer id) {
        log.info("[PersonServiceImpl > findById] {}", id);
        Optional<Person> person = repository.findPerson(id);
        log.info("[Person found] {}", person.orElse(null));
        String messageNotFound = messageSource.getMessage("not_found.person", null, locale);
        return person.orElseThrow(() -> new NotFoundException(messageNotFound));
    }

    @Override
    public void delete(Integer id) {
        log.info("[PersonServiceImpl > delete] {}", id);
        repository.delete(findById(id));
    }

}
