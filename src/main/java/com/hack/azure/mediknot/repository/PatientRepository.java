package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer>{

    public boolean existsByEmailId(String emailId);
}