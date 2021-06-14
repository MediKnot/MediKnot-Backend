package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Dosage;
import com.hack.azure.mediknot.entity.Prescription;

import java.util.List;

public class PrescriptionServiceImpl implements PrescriptionService{
    @Override
    public Prescription addPrescription(Integer consultationId, Prescription prescription) {
        return null;
    }

    @Override
    public Prescription getPrescriptionById(Integer id) {
        return null;
    }

    @Override
    public Prescription updatePrescriptionById(Integer id, Prescription prescription) {
        return null;
    }

    @Override
    public Prescription addDosages(Integer id, List<Dosage> dosageList) {
        return null;
    }

    @Override
    public Prescription clearDosages(Integer id) {
        return null;
    }

    @Override
    public void removePrescription(Integer id) {

    }
}
