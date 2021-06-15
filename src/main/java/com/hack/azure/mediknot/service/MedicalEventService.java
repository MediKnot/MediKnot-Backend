package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Report;

import java.util.List;

public interface MedicalEventService {

    public MedicalEvent addMedicalEvent(Integer patientId, MedicalEvent medicalEvent);

    public void removeMedicalEvent(Integer eventId);

    public MedicalEvent updateMedicalEventById(Integer id, MedicalEvent medicalEvent);

    public MedicalEvent getMedicalEvent(Integer id);

    public MedicalEvent closeMedicalEvent(Integer id);

    public MedicalEvent updateMedicalEvent(MedicalEvent medicalEvent);

    public void clearReports(Integer id);

    public MedicalEvent addReports(Integer id, List<Report> reports);

    public MedicalEvent addDisease(Integer eventId, Integer diseaseId);

    public MedicalEvent removeDisease(Integer eventId, Integer diseaseId);
}
