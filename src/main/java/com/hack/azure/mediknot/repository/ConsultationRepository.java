package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsultationRepository extends CrudRepository<Consultation, Integer> {

    List<Consultation> findAllByPatient(Patient patient);

}
