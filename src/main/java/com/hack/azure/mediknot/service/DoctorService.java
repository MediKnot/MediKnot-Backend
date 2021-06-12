package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Doctor;

public interface DoctorService {

    public Doctor createDoctor(Doctor doctor);
    public Doctor getDoctorById(Integer id);
}
