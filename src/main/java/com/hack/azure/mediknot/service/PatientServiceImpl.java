package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.repository.PatientRepository;

public class PatientServiceImpl {
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

}
