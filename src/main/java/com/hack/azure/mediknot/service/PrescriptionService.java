package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Dosage;
import com.hack.azure.mediknot.entity.Prescription;

import java.util.List;

public interface PrescriptionService {

    public Prescription addPrescription(Integer consultationId, Prescription prescription);

    public Prescription updatePrescriptionById(Integer id, Prescription prescription);

    public Prescription addDosages(Integer id, List<Dosage> dosageList);

    public Prescription clearDosages(Integer id);

    public void removePrescription(Integer id);

}
