package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.Disease;
import org.springframework.data.repository.CrudRepository;

public interface DiseaseRepository extends CrudRepository<Disease, Integer> {
}
