package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Disease;
import com.hack.azure.mediknot.exception.DiseaseException;
import com.hack.azure.mediknot.repository.DiseaseRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Disease> searchDiseases(String name) {
        List<Disease> result = new ArrayList<>();
        List<Disease> allDiseases = (List<Disease>) diseaseRepository.findAll();

        for (Disease disease:allDiseases){
            if(FuzzySearch.partialRatio(name, disease.getName()) >= 70){
                allDiseases.add(disease);
            }
        }
        return allDiseases;
    }
}
