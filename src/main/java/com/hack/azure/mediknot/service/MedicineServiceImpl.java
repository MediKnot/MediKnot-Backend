package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Medicine;
import com.hack.azure.mediknot.exception.MedicineException;
import com.hack.azure.mediknot.repository.MedicineRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<Medicine> result = new ArrayList<>();
        List<Medicine> medicines = (List<Medicine>) medicineRepository.findAll();

        for (Medicine medicine:medicines){
            if(FuzzySearch.partialRatio(name, medicine.getMedicineFullName()) >= 70){
                result.add(medicine);
            }
        }
        return result;
    }
}
