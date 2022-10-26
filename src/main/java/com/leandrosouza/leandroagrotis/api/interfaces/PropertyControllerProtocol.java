package com.leandrosouza.leandroagrotis.api.interfaces;

import com.leandrosouza.leandroagrotis.api.payload.response.PropertyResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface PropertyControllerProtocol {

    @ApiOperation("Listar todas as propriedades")
    @GetMapping
    ResponseEntity<List<PropertyResponse>> findAll();
}
