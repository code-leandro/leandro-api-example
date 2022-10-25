package com.leandrosouza.leandroagrotis.service.protocol;

import com.leandrosouza.leandroagrotis.domain.Person;

import java.util.List;

public interface PersonService {

    Person save(Person person);
    List<Person> findAll();
    Person findById(Integer id);
    void delete(Integer id);
}
