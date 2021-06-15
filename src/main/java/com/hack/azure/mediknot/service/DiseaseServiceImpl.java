package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Disease;
import com.hack.azure.mediknot.entity.Medicine;
import com.hack.azure.mediknot.exception.DiseaseException;
import com.hack.azure.mediknot.repository.DiseaseRepository;
import javafx.util.Pair;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        List<Pair<Disease, Integer>> result = new ArrayList<>();
        List<Disease> allDiseases = (List<Disease>) diseaseRepository.findAll();

        for (Disease disease:allDiseases){
            int partialRatio = FuzzySearch.partialRatio(name, disease.getName());
            if(partialRatio >= 70){
                result.add(new Pair(disease, partialRatio));
            }
        }
        return findTopK(result, 5);
    }

    private List<Disease> findTopK(List<Pair<Disease, Integer>> list, int k){
        Comparator<Pair<Disease, Integer>> comparator = new Comparator<Pair<Disease, Integer>>(){
            @Override
            public int compare(Pair<Disease, Integer> p1, Pair<Disease, Integer> p2){
                if(p1.getValue()==p2.getValue()){
                    if(p1.getKey().getId()<p2.getKey().getId()){
                        return 1;
                    }else{
                        return -1;
                    }
                }else{
                    return p1.getValue().compareTo(p2.getValue());
                }
            }
        };
        comparator = comparator.reversed();
        Set<Pair<Disease, Integer>> set = new TreeSet<>(comparator);
        set.addAll(list);
        return set.stream().map(pair -> pair.getKey()).limit(k).collect(Collectors.toList());
    }
}
