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
        List<List<Object>> result = new ArrayList<>();
        List<Medicine> medicines = (List<Medicine>) medicineRepository.findAll();

        for (Medicine medicine:medicines){
            int partialRatio = FuzzySearch.partialRatio(name, medicine.getMedicineFullName());
            if(partialRatio >= 70){
                List<Object> curr = new ArrayList<>();
                curr.add(medicine);
                curr.add(partialRatio);
                result.add(curr);
            }
        }
        List<Medicine> medicineList = findTopK(result, 5);
        return medicineList;
    }

    private List<Medicine> findTopK(List<List<Object>> list, int k){
        Comparator<List<Object>> comparator = new Comparator<List<Object>>(){
            @Override
            public int compare(List<Object> p1, List<Object> p2){
                if((Integer)p1.get(1)==(Integer)p2.get(1)){
                    if(((Medicine)p1.get(0)).getId()<((Medicine)p2.get(0)).getId()){
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
        return set.stream().map(pair -> (Medicine)pair.get(0)).limit(k).collect(Collectors.toList());
    }
}
