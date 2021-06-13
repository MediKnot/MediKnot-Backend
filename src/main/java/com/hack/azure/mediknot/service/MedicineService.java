package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Medicine;

public interface MedicineService {

    public Medicine addMedicine(Medicine medicine);

    public Medicine getMedicine(Integer id);

}
