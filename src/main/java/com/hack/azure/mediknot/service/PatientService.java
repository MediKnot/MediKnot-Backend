package com.hack.azure.mediknot.service;

public interface PatientService {
import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.User;

public interface PatientService {

    public Patient getPatientById(Integer id);

    public Patient updatePatientById(Integer id, Patient patient);

    public Patient createPatient(User patient);

}
