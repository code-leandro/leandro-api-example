package com.leandrosouza.leandroagrotis.service.impl;

import com.leandrosouza.leandroagrotis.domain.Laboratory;
import com.leandrosouza.leandroagrotis.exception.NotFoundException;
import com.leandrosouza.leandroagrotis.persistence.LaboratoryRepository;
import com.leandrosouza.leandroagrotis.service.protocol.LaboratoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
class LaboratoryServiceImpl implements LaboratoryService {

    private final LaboratoryRepository repository;
    private final MessageSource messageSource;
    private final Locale locale;

    @Override
    public List<Laboratory> findAll() {
        log.info("[LaboratoryService > findAll]");
        return repository.findAll();
    }

    @Override
    public Laboratory findById(Integer id) {
        log.info("[LaboratoryService > findById]");
        if (id == null)
            return null;
        return repository.findById(id).orElse(null);
    }

    @Override
    public Laboratory findByIdOrThrowsNotFoundException(Integer id) {
        log.info("[LaboratoryService > findByIdOrThrowsNotFoundException]");
        return repository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("not_found.laboratory", null, locale)));
    }
}
