package com.leandrosouza.leandroagrotis.api.interfaces;

import com.leandrosouza.leandroagrotis.api.payload.response.LaboratoryResponse;
import com.leandrosouza.leandroagrotis.api.payload.response.ListLaboratoryResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LaboratoryControllerProtocol {

    @ApiOperation(value = "Listar todos os laboratórios")
    ResponseEntity<ListLaboratoryResponse> findAll();
}
