package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.Consultation;
import org.springframework.data.repository.CrudRepository;

public interface ConsultationRepository extends CrudRepository<Consultation, Integer> {
}
