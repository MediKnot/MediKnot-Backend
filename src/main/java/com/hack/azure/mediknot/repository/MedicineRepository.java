package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.Medicine;
import org.springframework.data.repository.CrudRepository;

public interface MedicineRepository extends CrudRepository<Medicine, Integer> {
}
