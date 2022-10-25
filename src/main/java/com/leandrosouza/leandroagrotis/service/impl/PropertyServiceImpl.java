package com.leandrosouza.leandroagrotis.service.impl;

import com.leandrosouza.leandroagrotis.domain.Property;
import com.leandrosouza.leandroagrotis.exception.NotFoundException;
import com.leandrosouza.leandroagrotis.persistence.PropertyRepository;
import com.leandrosouza.leandroagrotis.service.protocol.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository repository;
    private final MessageSource messageSource;
    private final Locale locale;

    public List<Property> findAll() {

        log.info("[PropertyService > findAll]");
        return repository.findAll();
    }

    @Override
    public Property findById(Integer id) {
        if (id == null)
            return null;

        return repository.findById(id).orElse(null);
    }

    @Override
    public Property findByIdOrThrowsNotFoundException(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("not_found.property", null, locale)));
    }
}
