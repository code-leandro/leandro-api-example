package com.leandrosouza.leandroagrotis.api.controller;

import com.leandrosouza.leandroagrotis.api.payload.request.PersonRequest;
import com.leandrosouza.leandroagrotis.api.payload.response.PersonResponse;
import com.leandrosouza.leandroagrotis.domain.Person;
import com.leandrosouza.leandroagrotis.service.protocol.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.List;

@RestController
@RequestMapping("/person")
@Slf4j
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable Integer id) {
        Person person = service.findById(id);
        PersonResponse personResponse = PersonResponse.fromModel(person);
        return ResponseEntity.ok().body(personResponse);
    }

    @PostMapping
    public ResponseEntity<PersonResponse> save(@RequestBody @Validated PersonRequest request) {
        log.info("[Person > save] {}", request);
        Person person = service.save(request.toModel());

        UriComponents uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId());
        return ResponseEntity.created(uri.toUri()).body(PersonResponse.fromModel(person));
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> findAll() {
        log.info("[Person > findAll]");
        return ResponseEntity.ok(service.findAll().stream().map(PersonResponse::fromModel).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Integer id, @RequestBody @Validated PersonRequest personRequest) {
        service.findById(id);
        Person person = personRequest.toModel();
        person.setId(id);
        person = service.save(person);
        return ResponseEntity.ok().body(PersonResponse.fromModel(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
