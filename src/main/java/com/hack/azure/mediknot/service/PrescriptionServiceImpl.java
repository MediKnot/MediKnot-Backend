package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.Dosage;
import com.hack.azure.mediknot.entity.Medicine;
import com.hack.azure.mediknot.entity.Prescription;
import com.hack.azure.mediknot.exception.ConsultationException;
import com.hack.azure.mediknot.exception.PrescriptionException;
import com.hack.azure.mediknot.repository.MedicineRepository;
import com.hack.azure.mediknot.repository.PrescriptionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{

    private PrescriptionRepository prescriptionRepository;
    private ConsultationService consultationService;
    private MedicineRepository medicineRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, ConsultationService consultationService, MedicineRepository medicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.consultationService = consultationService;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Prescription addPrescription(Integer consultationId, Prescription prescription) {
        try{
            Consultation consultation = consultationService.getConsultation(consultationId);
            prescription = prescriptionRepository.save(prescription);
            Set<Prescription> prescriptionSet = consultation.getPrescriptionList();

            if(prescriptionSet == null)
                prescriptionSet = new HashSet<>();

            prescriptionSet.add(prescription);
            consultation.setPrescriptionList(prescriptionSet);
            consultationService.updateConsultation(consultation);
            return prescription;
        } catch (ConsultationException e){
            throw new PrescriptionException("Consultation not found, couldn't add prescription", 404);
        }
    }

    @Override
    public Prescription getPrescriptionById(Integer id) throws PrescriptionException{
        return prescriptionRepository.findById(id).orElseThrow(() -> new PrescriptionException("Prescription not found",404));
    }

    @Override
    public Prescription updatePrescriptionById(Integer id, Prescription prescription) throws PrescriptionException{
        Prescription existingPrescription = getPrescriptionById(id);
        BeanUtils.copyProperties(prescription, existingPrescription);
        return updatePrescription(existingPrescription);
    }

    @Override
    public Prescription addDosages(Integer id, List<Dosage> dosageList) {
        Prescription prescription;
        try{
            prescription = getPrescriptionById(id);
        }catch (PrescriptionException e){
            throw new PrescriptionException("Prescription not found, couldn't add dosages", 404);
        }

        List<Integer> medicineIds = dosageList.stream().map(
                dosage -> dosage.getMedicine().getId()
        ).collect(Collectors.toList());

        List<Medicine> medicineList = (List<Medicine>) medicineRepository.findAllById(medicineIds);

        for(int i=0;i<dosageList.size();i++){
            dosageList.get(i).setMedicine(medicineList.get(i));
        }
        List<Dosage> dosages = prescription.getDosageList();
        if(dosages == null)
            dosages = new ArrayList<>();

        dosages.addAll(dosageList);
        prescription.setDosageList(dosages);
        return updatePrescription(prescription);
    }

    @Override
    public Prescription clearDosages(Integer id) throws PrescriptionException{
        Prescription prescription = getPrescriptionById(id);
        prescription.setDosageList(new ArrayList<>());
        return updatePrescription(prescription);
    }

    @Override
    public void removePrescription(Integer id) throws PrescriptionException{
        Prescription prescription = getPrescriptionById(id);
        prescriptionRepository.delete(prescription);
    }

    @Override
    public Prescription updatePrescription(Prescription prescription) throws PrescriptionException{
        if(prescription.getId()==null){
            throw new PrescriptionException("Id not present in Prescription body", 204);
        }
        return prescriptionRepository.save(prescription);
    }
}
