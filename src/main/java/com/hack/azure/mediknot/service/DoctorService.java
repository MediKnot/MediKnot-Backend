package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Doctor;

public interface DoctorService {

    public Doctor createDoctor(Doctor doctor);

    public Doctor getDoctorById(Integer id);

    public Doctor updateDoctorById(Integer id, Doctor doctor);

    public Doctor addDegree(Integer id, String degree);

    public Doctor addSpecialization(Integer id, String specialization);

    public Doctor addMedicalCouncilAffiliation(Integer id, String medicalCouncilAffiliation);

    public Doctor addClinicName(Integer id, String clinicName);

}
