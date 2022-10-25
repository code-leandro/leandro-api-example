package com.leandrosouza.leandroagrotis.api.controller;

import com.leandrosouza.leandroagrotis.api.payload.response.LaboratoryResponse;
import com.leandrosouza.leandroagrotis.domain.Laboratory;
import com.leandrosouza.leandroagrotis.service.protocol.LaboratoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/laboratory")
@Slf4j
@RequiredArgsConstructor
public class LaboratoryController {

    private final LaboratoryService service;

    @GetMapping
    public ResponseEntity<List<LaboratoryResponse>> findAll() {
        List<Laboratory> list = service.findAll();
        List<LaboratoryResponse> listResponse = list.stream().map(LaboratoryResponse::fromModel).toList();
        return ResponseEntity.ok(listResponse);
    }
}
