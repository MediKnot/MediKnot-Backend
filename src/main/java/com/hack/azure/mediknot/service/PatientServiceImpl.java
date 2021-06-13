package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.User;
import com.hack.azure.mediknot.exception.PatientException;
import com.hack.azure.mediknot.repository.PatientRepository;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Patient createPatient(Patient patient) throws PatientException {
        if(patientRepository.existsByEmailId(patient.getEmailId())){
            throw new PatientException("Patient with email id already exists", 409);
        }
        return patientRepository.save(patient);
    }
}
