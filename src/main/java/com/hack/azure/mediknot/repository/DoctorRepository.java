package com.hack.azure.mediknot.repository;

import com.hack.azure.mediknot.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Integer>{

    public Boolean existsByRegistrationNumber(String registrationNumber);

    public Doctor findByRegistrationNumber(String registrationNumber);

    public boolean existsByEmailId(String emailId);

    public Doctor findByPhoneNumber(String phoneNumber);

}