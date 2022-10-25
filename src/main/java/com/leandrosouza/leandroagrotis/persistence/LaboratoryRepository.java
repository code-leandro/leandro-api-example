package com.leandrosouza.leandroagrotis.persistence;

import com.leandrosouza.leandroagrotis.domain.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Integer> {
}
