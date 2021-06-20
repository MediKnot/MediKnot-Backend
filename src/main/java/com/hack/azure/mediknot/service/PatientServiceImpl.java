package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.config.BeanNotNullCopy;
import com.hack.azure.mediknot.dto.DoctorDto;
import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.Report;
import com.hack.azure.mediknot.entity.User;
import com.hack.azure.mediknot.exception.PatientException;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getPatientById(Integer id) throws PatientException{
        return patientRepository.findById(id).orElseThrow(() -> new PatientException("Patient not found.", 404));
    }

    @Override
    public Patient updatePatientById(Integer id, Patient patient) throws PatientException{
        Patient existingPatient;
        try{
            existingPatient = getPatientById(id);
        }catch (UserException e){
            throw new UserException("Couldn't update, patient not found", 404);
        }
        BeanNotNullCopy.copyNonNullProperties(patient,existingPatient);
        return patientRepository.save(existingPatient);
    }

    @Override
    public Patient createPatient(Patient patient) throws PatientException {
        if(patientRepository.existsByEmailId(patient.getEmailId())){
            throw new PatientException("Patient with email id already exists", 409);
        }
        try{
            patient = patientRepository.save(patient);
        }catch (DataIntegrityViolationException exception){
            throw new PatientException("User with phone number already exists.", 409);
        }
        return patient;
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
    public Patient addAllergies(Integer id, List<String> allergies) throws PatientException{
        Patient patient = getPatientById(id);
        if(patient.getAllergies() == null){
            patient.setAllergies(new ArrayList<>());
        }
        patient.getAllergies().addAll(allergies);
        return updatePatient(patient);
    }

    @Override
    public void clearReports(Integer id) throws PatientException{
        Patient patient = getPatientById(id);
        if(patient.getGeneralReports()==null && patient.getGeneralReports().isEmpty()){
            throw new PatientException("Reports are not present of Patient", 204);
        }
        patient.getGeneralReports().clear();
        updatePatient(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) throws UserException {
        if(patient.getId()==null){
            throw new UserException("Id not present in Patient body", 204);
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientByPhoneNumber(String phoneNumber) {
        Patient patient = patientRepository.findByPhoneNumber(phoneNumber);
        if(patient==null)
            throw new PatientException("Patient not found with phone number", 404);
        return patient;
    }
}
