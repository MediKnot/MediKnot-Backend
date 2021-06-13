package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.Report;
import com.hack.azure.mediknot.entity.User;
import com.hack.azure.mediknot.exception.PatientException;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientException("Patient not found.", 404));
    }

    @Override
    public Patient updatePatientById(Integer id, Patient patient) {
        Patient existingPatient;
        try{
            existingPatient = getPatientById(id);
        }catch (UserException e){
            throw new UserException("Couldn't update, patient not found", 404);
        }
        BeanUtils.copyProperties(patient, existingPatient);
        return patientRepository.save(existingPatient);
    }

    @Override
    public Patient createPatient(Patient patient) throws PatientException {
        if(patientRepository.existsByEmailId(patient.getEmailId())){
            throw new PatientException("Patient with email id already exists", 409);
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient addReports(Integer id, List<Report> reports) throws PatientException{
        Patient patient = getPatientById(id);
        if(patient.getGeneralReports() == null){
            patient.setGeneralReports(new ArrayList<>());
        }
        patient.getGeneralReports().addAll(reports);
        return updatePatient(patient);
    }



    @Override
    public Patient addAllergies(Integer id, List<String> allergies) {
        Patient patient = getPatientById(id);
        if(patient.getAllergies() == null){
            patient.setAllergies(new ArrayList<>());
        }
        patient.getAllergies().addAll(allergies);
        return updatePatient(patient);
    }

    @Override
    public void clearReports(Integer id) throws UserException{
        Patient patient = getPatientById(id);
        if(patient.getGeneralReports()==null){
            return;
        }
        patient.getGeneralReports().clear();
        updatePatient(patient);
    }

    private Patient updatePatient(Patient patient) throws UserException {
        if(patient.getId()==null){
            throw new UserException("Id not present in Patient body", 204);
        }
        return patientRepository.save(patient);
    }
}
