package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Treatment;
import com.hack.azure.mediknot.exception.ConsultationException;
import com.hack.azure.mediknot.exception.MedicalEventException;
import com.hack.azure.mediknot.repository.ConsultationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private ConsultationRepository consultationRepository;
    private MedicalEventService medicalEventService;
    private DoctorService doctorService;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository, MedicalEventService medicalEventService, DoctorService doctorService){
        this.medicalEventService = medicalEventService;
        this.consultationRepository = consultationRepository;
        this.doctorService = doctorService;
    }

    @Override
    public Consultation addConsultation(Integer eventId, Integer doctorId, Consultation consultation) {
        MedicalEvent medicalEvent = medicalEventService.getMedicalEvent(eventId);
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (medicalEvent.getIsActive()){
            consultation.setDoctor(doctor);
            consultation = consultationRepository.save(consultation);
            if (medicalEvent.getConsultationList() == null){
                medicalEvent.setConsultationList(new HashSet<>());
            }
            medicalEvent.getConsultationList().add(consultation);
            medicalEventService.updateMedicalEvent(medicalEvent);
            return consultation;
        }
        else {
            throw new MedicalEventException("Medical event with event id "+ eventId + "already closed", 204);
        }
    }

    @Override
    public Consultation addConsultation(Integer doctorId, Consultation consultation) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        consultation.setDoctor(doctor);
        return consultationRepository.save(consultation);
    }

    @Override
    public void removeConsultation(Integer id) {
        Consultation consultation = getConsultation(id);
        consultationRepository.delete(consultation);
    }

    @Override
    public Consultation updateConsultationById(Integer id, Consultation consultation) {
        Consultation existingConsultation;
        try{
            existingConsultation = getConsultation(id);
        }catch (ConsultationException e){
            throw new ConsultationException("Couldn't update, consultation not found", 404);
        }
        BeanUtils.copyProperties(consultation, existingConsultation);
        return consultationRepository.save(existingConsultation);
    }

    @Override
    public Consultation getConsultation(Integer id) {
        return consultationRepository.findById(id).orElseThrow(() -> new ConsultationException("Consultation not found.", 404));
    }

    @Override
    public Consultation updateConsultation(Consultation consultation) {
        if(consultation.getId()==null){
            throw new ConsultationException("Id not present in consultation body", 204);
        }
        return consultationRepository.save(consultation);
    }

    @Override
    public void clearNotes(Integer id) {
        Consultation consultation = getConsultation(id);
        if(consultation.getNotes()==null){
            return;
        }
        consultation.getNotes().clear();
        updateConsultation(consultation);
    }

    @Override
    public Consultation addNotes(Integer id, List<String> notes) {
        Consultation consultation = getConsultation(id);
        if(consultation.getNotes()==null){
            consultation.setNotes(new ArrayList<>());
        }
        consultation.getNotes().addAll(notes);
        return updateConsultation(consultation);
    }

    @Override
    public void clearTreatment(Integer id) {
        Consultation consultation = getConsultation(id);
        if(consultation.getTreatmentList()==null){
            return;
        }
        consultation.getTreatmentList().clear();
        updateConsultation(consultation);
    }

    @Override
    public Consultation addTreatment(Integer id, List<Treatment> treatmentList) {
        Consultation consultation = getConsultation(id);
        if(consultation.getTreatmentList()==null){
            consultation.setTreatmentList(new ArrayList<>());
        }
        consultation.getTreatmentList().addAll(treatmentList);
        return updateConsultation(consultation);
    }
}
