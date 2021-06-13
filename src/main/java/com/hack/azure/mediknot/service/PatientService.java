package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Patient;

public interface PatientService {

    public Patient getPatientById(Integer id);

    public Patient updatePatientById(Integer id, Patient patient);

    public Patient createPatient(Patient patient);

}
