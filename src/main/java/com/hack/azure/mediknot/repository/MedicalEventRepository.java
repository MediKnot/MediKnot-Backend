package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.MedicalEvent;
import org.springframework.data.repository.CrudRepository;

public interface MedicalEventRepository extends CrudRepository<MedicalEvent, Integer> {
}
