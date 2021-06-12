package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.Prescription;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<Prescription, Integer> {
}
