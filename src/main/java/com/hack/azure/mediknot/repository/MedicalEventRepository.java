package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicalEventRepository extends CrudRepository<MedicalEvent, Integer> {
    List<MedicalEvent> findAllByPatient(Patient patient);
}
