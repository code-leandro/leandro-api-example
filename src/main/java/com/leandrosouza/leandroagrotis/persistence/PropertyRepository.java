package com.leandrosouza.leandroagrotis.persistence;

import com.leandrosouza.leandroagrotis.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
