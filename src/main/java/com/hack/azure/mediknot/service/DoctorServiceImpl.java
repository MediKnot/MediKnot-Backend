package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.exception.DoctorException;
import com.hack.azure.mediknot.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor getDoctorById(Integer id) {
        return null;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return null;
    }
}
