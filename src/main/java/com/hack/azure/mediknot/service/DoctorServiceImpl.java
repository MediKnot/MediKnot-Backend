package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.exception.DoctorException;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.repository.DoctorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor getDoctorById(Integer id) throws DoctorException{
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorException("Doctor not found", 404));
        return doctor;
    }

    @Override
    public Doctor updateDoctorById(Integer id, Doctor doctor) {
        Doctor existingDoctor;
        try{
            existingDoctor = getDoctorById(id);
        }catch (UserException e){
            throw new UserException("Couldn't update, patient not found", 404);
        }
        BeanUtils.copyProperties(doctor, existingDoctor);
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public Doctor addDegree(Integer id, String degree) {
        Doctor existingDoctor;
        try{
            existingDoctor = getDoctorById(id);
        }catch (UserException e){
            throw new UserException("Couldn't update, doctor not found", 404);
        }
        if (existingDoctor.getDegree() == null){
            existingDoctor.setDegree(new ArrayList<>());
        }
        existingDoctor.getDegree().add(degree);
        return updateDoctor(existingDoctor);
    }

    @Override
    public Doctor addSpecialization(Integer id, String specialization) {
        Doctor existingDoctor;
        try{
            existingDoctor = getDoctorById(id);
        }catch (UserException e){
            throw new UserException("Couldn't update, doctor not found", 404);
        }
        if (existingDoctor.getSpecialization() == null){
            existingDoctor.setSpecialization(new ArrayList<>());
        }
        existingDoctor.getSpecialization().add(specialization);
        return updateDoctor(existingDoctor);
    }

    @Override
    public Doctor addMedicalCouncilAffiliation(Integer id, String medicalCouncilAffiliation) {
        Doctor existingDoctor;
        try{
            existingDoctor = getDoctorById(id);
        }catch (UserException e){
            throw new UserException("Couldn't update, doctor not found", 404);
        }
        if (existingDoctor.getMedicalCouncilAffiliation() == null){
            existingDoctor.setMedicalCouncilAffiliation(new ArrayList<>());
        }
        existingDoctor.getMedicalCouncilAffiliation().add(medicalCouncilAffiliation);
        return updateDoctor(existingDoctor);
    }

    @Override
    public Doctor addClinicName(Integer id, String clinicName) {
        Doctor existingDoctor;
        try{
            existingDoctor = getDoctorById(id);
        }catch (UserException e){
            throw new UserException("Couldn't update, doctor not found", 404);
        }
        if (existingDoctor.getClinicName() == null){
            existingDoctor.setClinicName(new ArrayList<>());
        }
        existingDoctor.getClinicName().add(clinicName);
        return updateDoctor(existingDoctor);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) throws DoctorException {
        if(doctorRepository.existsByEmailId(doctor.getEmailId())){
            throw new DoctorException("Doctor with email Id exists.", 409);
        }
        return doctorRepository.save(doctor);
    }

    private Doctor updateDoctor(Doctor doctor) throws UserException {
        if(doctor.getId()==null){
            throw new UserException("Id not present in doctor body", 204);
        }
        return doctorRepository.save(doctor);
    }
}
