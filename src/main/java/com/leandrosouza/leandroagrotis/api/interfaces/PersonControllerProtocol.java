package com.leandrosouza.leandroagrotis.api.interfaces;

import com.leandrosouza.leandroagrotis.api.payload.request.PersonRequest;
import com.leandrosouza.leandroagrotis.api.payload.response.PersonResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PersonControllerProtocol {

    @ApiOperation("Buscar pelo id")
    @GetMapping("/{id}")
    ResponseEntity<PersonResponse> findById(@PathVariable Integer id);

    @ApiOperation("Salvar novo item (cadastro)")
    @PostMapping
    ResponseEntity<PersonResponse> save(@RequestBody @Validated PersonRequest request);

    @ApiOperation("Listar todos")
    @GetMapping
    ResponseEntity<List<PersonResponse>> findAll();

    @ApiOperation("Atualizar um item")
    @PutMapping("/{id}")
    ResponseEntity<PersonResponse> update(@PathVariable Integer id, @RequestBody @Validated PersonRequest personRequest);

    @ApiOperation("Deletar pelo id (delete físico)")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id);
}
