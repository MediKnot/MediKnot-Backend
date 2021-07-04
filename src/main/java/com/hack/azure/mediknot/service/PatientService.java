package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.ProfileViews;
import com.hack.azure.mediknot.entity.Report;

import java.util.List;

public interface PatientService {

    public Patient getPatientById(Integer id);

    public Patient updatePatientById(Integer id, Patient patient);

    public Patient createPatient(Patient patient);

    public Patient addReports(Integer id, List<Report> reports);

    public Patient addAllergies(Integer id, List<String> allergies);

    public void clearReports(Integer id);

    public Patient updatePatient(Patient patient);

    public Patient getPatientByPhoneNumber(String phoneNumber);

    public void sharePatientProfile(Integer id, String name, String emailId);

    public ProfileViews addView(Integer id, String name, String email);

    public List<ProfileViews> getAllViews(Integer id);

}
