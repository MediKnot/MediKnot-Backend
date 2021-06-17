package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.Treatment;

import java.util.List;

public interface ConsultationService {

    public Consultation addConsultation(Integer eventId, Integer doctorId, Consultation consultation);

    public Consultation addConsultation(Integer doctorId, Consultation consultation, Integer patientId);

    public void removeConsultation(Integer id);

    public Consultation updateConsultationById(Integer id, Consultation consultation);

    public Consultation getConsultation(Integer id);

    public Consultation updateConsultation(Consultation consultation);

    public void clearNotes(Integer id);

    public Consultation addNotes(Integer id, List<String> notes);

    public void clearTreatment(Integer id);

    public Consultation addTreatment(Integer id, List<Treatment> treatmentList);

    public List<Consultation> getConsultationListOfPatient(Integer patientId);

}
