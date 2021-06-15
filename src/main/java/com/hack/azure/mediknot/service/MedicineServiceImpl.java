package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Disease;
import com.hack.azure.mediknot.entity.Medicine;
import com.hack.azure.mediknot.exception.MedicineException;
import com.hack.azure.mediknot.repository.MedicineRepository;
import javafx.util.Pair;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public Medicine addMedicine(Medicine medicine){
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine getMedicine(Integer id) throws MedicineException{
        return medicineRepository.findById(id).orElseThrow(()->new MedicineException("Medicine not found", 404));
    }

    @Override
    public List<Medicine> searchMedicine(String name) {
        List<Pair<Medicine, Integer>> result = new ArrayList<>();
        List<Medicine> medicines = (List<Medicine>) medicineRepository.findAll();

        for (Medicine medicine:medicines){
            int partialRatio = FuzzySearch.partialRatio(name, medicine.getMedicineFullName());
            if(partialRatio >= 70){
                result.add(new Pair<>(medicine, partialRatio));
            }
        }
        List<Medicine> medicineList = findTopK(result, 5);
        return medicineList;
    }

    private List<Medicine> findTopK(List<Pair<Medicine, Integer>> list, int k){
        Comparator<Pair<Medicine, Integer>> comparator = new Comparator<Pair<Medicine, Integer>>(){
            @Override
            public int compare(Pair<Medicine, Integer> p1, Pair<Medicine, Integer> p2){
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
        Set<Pair<Medicine, Integer>> set = new TreeSet<>(comparator);
        set.addAll(list);
        return set.stream().map(pair -> pair.getKey()).limit(k).collect(Collectors.toList());
    }
}
