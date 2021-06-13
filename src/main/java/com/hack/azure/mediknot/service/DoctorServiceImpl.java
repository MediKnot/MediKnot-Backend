package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.entity.User;
import com.hack.azure.mediknot.exception.DoctorException;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.repository.DoctorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor getDoctorById(Integer id) throws DoctorException{
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorException("Doctor not foound", 404));
        return doctor;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) throws DoctorException {
        if(doctorRepository.existsByEmailId(doctor.getEmailId())){
            throw new DoctorException("Doctor with email Id exists.", 409);
        }
        return doctorRepository.save(doctor);
    }
}
