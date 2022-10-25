package com.leandrosouza.leandroagrotis.persistence;

import com.leandrosouza.leandroagrotis.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("select p from Person p join fetch p.property property join fetch p.laboratory l where p.id = ?1")
    Optional<Person> findPerson(Integer id);
}
