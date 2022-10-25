package com.leandrosouza.leandroagrotis.service.protocol;

import com.leandrosouza.leandroagrotis.domain.Property;

import java.util.List;

public interface PropertyService {

    List<Property> findAll();

    Property findById(Integer id);

    Property findByIdOrThrowsNotFoundException(Integer id);
}
