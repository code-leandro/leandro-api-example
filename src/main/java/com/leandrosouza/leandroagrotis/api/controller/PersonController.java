package com.leandrosouza.leandroagrotis.api.controller;

import com.leandrosouza.leandroagrotis.api.interfaces.PersonControllerProtocol;
import com.leandrosouza.leandroagrotis.api.payload.request.PersonRequest;
import com.leandrosouza.leandroagrotis.api.payload.response.ListPersonResponse;
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
@RequestMapping("/v1/person")
@Slf4j
@RequiredArgsConstructor
public class PersonController implements PersonControllerProtocol {

    private final PersonService service;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable Integer id) {
        Person person = service.findById(id);
        PersonResponse personResponse = PersonResponse.fromModel(person);
        return ResponseEntity.ok().body(personResponse);
    }

    @Override
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

    @Override
    @GetMapping
    public ResponseEntity<ListPersonResponse> findAll() {
        log.info("[Person > findAll]");
        List<PersonResponse> list = service.findAll().stream().map(PersonResponse::fromModel).toList();
        return ResponseEntity.ok(new ListPersonResponse(list));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Integer id, @RequestBody @Validated PersonRequest personRequest) {
        service.findById(id);
        Person person = personRequest.toModel();
        person.setId(id);
        person = service.save(person);
        return ResponseEntity.ok().body(PersonResponse.fromModel(person));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
