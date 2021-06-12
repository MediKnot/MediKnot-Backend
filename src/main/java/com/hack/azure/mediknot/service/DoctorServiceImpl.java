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
    public Doctor createDoctor(Doctor doctor) throws DoctorException{
        if(doctorRepository.existsByRegistrationNumber(doctor.getEmailId())){
            throw new DoctorException("Doctor with registration number exists.", 409);
        }
        Doctor createdDoctor = doctorRepository.save(doctor);
        return  createdDoctor;
    }
}
