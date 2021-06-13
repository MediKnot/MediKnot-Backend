package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Medicine;

import java.util.List;

public interface MedicineService {

    public Medicine addMedicine(Medicine medicine);

    public Medicine getMedicine(Integer id);

    List<Medicine> searchMedicine(String name);

}
