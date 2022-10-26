package com.leandrosouza.leandroagrotis.service.protocol;

import com.leandrosouza.leandroagrotis.domain.Laboratory;

import java.util.List;

public interface LaboratoryService {

    List<Laboratory> findAll();
    Laboratory findById(Integer id);
    Laboratory findByIdOrThrowsNotFoundException(Integer id);
}
