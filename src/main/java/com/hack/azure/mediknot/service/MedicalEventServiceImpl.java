package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.Report;
import com.hack.azure.mediknot.exception.MedicalEventException;
import com.hack.azure.mediknot.exception.PatientException;
import com.hack.azure.mediknot.repository.MedicalEventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalEventServiceImpl implements MedicalEventService {

    private MedicalEventRepository medicalEventRepository;
    private PatientService patientService;

    public MedicalEventServiceImpl(MedicalEventRepository medicalEventRepository, PatientService patientService) {
        this.medicalEventRepository = medicalEventRepository;
        this.patientService = patientService;
    }

    @Override
    public MedicalEvent addMedicalEvent(Integer patientId, MedicalEvent medicalEvent) throws MedicalEventException{
        try {
            Patient patient = patientService.getPatientById(patientId);
            medicalEvent.setPatient(patient);
            return medicalEventRepository.save(medicalEvent);
        }catch (PatientException e){
            throw new MedicalEventException("Patient not found, couldn't add event", 404);
        }
    }

    @Override
    public void removeMedicalEvent(Integer eventId) throws MedicalEventException{
        MedicalEvent medicalEvent = getMedicalEvent(eventId);
        medicalEventRepository.delete(medicalEvent);
    }

    @Override
    public MedicalEvent updateMedicalEventById(Integer id, MedicalEvent medicalEvent) {
        MedicalEvent existingMedicalEvent;
        try{
            existingMedicalEvent = getMedicalEvent(id);
        }catch (MedicalEventException e){
            throw new MedicalEventException("Couldn't update, event not found", 404);
        }
        BeanUtils.copyProperties(medicalEvent, existingMedicalEvent);
        return medicalEventRepository.save(existingMedicalEvent);
    }

    @Override
    public MedicalEvent getMedicalEvent(Integer id) {
        return medicalEventRepository.findById(id).orElseThrow(() -> new MedicalEventException("Event not found.", 404));
    }

    @Override
    public MedicalEvent closeMedicalEvent(Integer id) throws MedicalEventException{
        MedicalEvent medicalEvent = getMedicalEvent(id);
        medicalEvent.setEndDate(LocalDate.now());
        medicalEvent.setIsActive(Boolean.FALSE);
        return updateMedicalEvent(medicalEvent);
    }

    @Override
    public MedicalEvent updateMedicalEvent(MedicalEvent medicalEvent) {
        if(medicalEvent.getId()==null){
            throw new MedicalEventException("Id not present in Event body", 204);
        }
        return medicalEventRepository.save(medicalEvent);
    }

    @Override
    public void clearReports(Integer id) {
        MedicalEvent medicalEvent = getMedicalEvent(id);
        if(medicalEvent.getReports()==null){
            return;
        }
        medicalEvent.getReports().clear();
        updateMedicalEvent(medicalEvent);
    }

    @Override
    public MedicalEvent addReports(Integer id, List<Report> reports) {
        MedicalEvent medicalEvent = getMedicalEvent(id);
        if(medicalEvent.getReports()==null){
            medicalEvent.setReports(new ArrayList<>());
        }
        medicalEvent.getReports().addAll(reports);
        return updateMedicalEvent(medicalEvent);
    }
}
