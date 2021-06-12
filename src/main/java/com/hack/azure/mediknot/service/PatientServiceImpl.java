package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.User;
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
        return null;
    }

    @Override
    public Patient updatePatientById(Integer id, Patient patient) {
        return null;
    }

    @Override
    public Patient createPatient(User patient) {
        return null;
    }
}
