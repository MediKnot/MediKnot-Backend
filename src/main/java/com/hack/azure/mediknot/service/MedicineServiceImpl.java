package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Medicine;
import com.hack.azure.mediknot.exception.MedicineException;
import com.hack.azure.mediknot.repository.MedicineRepository;

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
}
