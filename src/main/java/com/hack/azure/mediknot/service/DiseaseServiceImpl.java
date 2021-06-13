package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Disease;
import com.hack.azure.mediknot.exception.DiseaseException;
import com.hack.azure.mediknot.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

@Service
public class DiseaseServiceImpl implements DiseaseService{

    private DiseaseRepository diseaseRepository;

    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public Disease addDisease(Disease disease) {
        return diseaseRepository.save(disease);
    }

    @Override
    public Disease getDisease(Integer id) {
        return diseaseRepository.findById(id).orElseThrow(() -> new DiseaseException("Disease not found", 404));
    }
}
