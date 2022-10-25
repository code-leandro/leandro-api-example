package com.leandrosouza.leandroagrotis.api.controller;

import com.leandrosouza.leandroagrotis.domain.Property;
import com.leandrosouza.leandroagrotis.service.protocol.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/property")
@Slf4j
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService service;

    @GetMapping
    public ResponseEntity<List<Property>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
