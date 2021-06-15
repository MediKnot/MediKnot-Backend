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
        List<List<Object>> result = new ArrayList<>();
        List<Disease> allDiseases = (List<Disease>) diseaseRepository.findAll();

        for (Disease disease:allDiseases){
            int partialRatio = FuzzySearch.partialRatio(name, disease.getName());
            if(partialRatio >= 70){
                List<Object> curr = new ArrayList<>();
                curr.add(disease);
                curr.add(partialRatio);
                result.add(curr);
            }
        }
        return findTopK(result, 5);
    }

    private List<Disease> findTopK(List<List<Object>> list, int k){
        Comparator<List<Object>> comparator = new Comparator<List<Object>>(){
            @Override
            public int compare(List<Object> p1, List<Object> p2){
                if((Integer)p1.get(1)==(Integer)p2.get(1)){
                    if(((Disease)p1.get(0)).getId()<((Disease)p2.get(0)).getId()){
                        return 1;
                    }else{
                        return -1;
                    }
                }else if((Integer)p1.get(1)<(Integer)p2.get(1)) {
                    return -1;
                }else {
                    return 1;
                }
            }
        };
        comparator = comparator.reversed();
        Set<List<Object>> set = new TreeSet<>(comparator);
        set.addAll(list);
        return set.stream().map(pair -> (Disease)pair.get(0)).limit(k).collect(Collectors.toList());
    }
}
