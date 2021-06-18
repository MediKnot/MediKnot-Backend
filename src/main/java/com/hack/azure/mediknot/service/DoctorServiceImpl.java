package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.config.BeanNotNullCopy;
import com.hack.azure.mediknot.entity.Disease;
import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.exception.DoctorException;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.repository.DoctorRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        BeanNotNullCopy.copyNonNullProperties(doctor,existingDoctor);
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
    public Doctor getDoctorByPhoneNumber(String phoneNumber) {
        Doctor doctor = doctorRepository.findByPhoneNumber(phoneNumber);
        if(doctor == null)
            throw new DoctorException("Doctor not found with phone number", 404);
        return doctor;
    }

    @Override
    public List<Doctor> searchDoctors(String name) {
        List<List<Object>> result = new ArrayList<>();
        List<Doctor> allDoctors = getAllDoctors();

        for (Doctor doctor:allDoctors){
            int partialRatio = FuzzySearch.partialRatio(name, doctor.getName());
            if(partialRatio >= 70){
                List<Object> curr = new ArrayList<>();
                curr.add(doctor);
                curr.add(partialRatio);
                result.add(curr);
            }
        }
        return findTopK(result, result.size());
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    public Doctor createDoctor(Doctor doctor) throws DoctorException {
        if(doctorRepository.existsByEmailId(doctor.getEmailId())){
            throw new DoctorException("Doctor with email Id exists.", 409);
        }
        try{
            doctor = doctorRepository.save(doctor);
        }catch (DataIntegrityViolationException exception){
            throw new DoctorException(exception.getMessage(), 500);
        }
        return doctor;
    }

    private Doctor updateDoctor(Doctor doctor) throws UserException {
        if(doctor.getId()==null){
            throw new UserException("Id not present in doctor body", 204);
        }
        return doctorRepository.save(doctor);
    }

    private List<Doctor> findTopK(List<List<Object>> list, int k){
        Comparator<List<Object>> comparator = new Comparator<List<Object>>(){
            @Override
            public int compare(List<Object> p1, List<Object> p2){
                if((Integer)p1.get(1)==(Integer)p2.get(1)){
                    if(((Doctor)p1.get(0)).getId()<((Doctor)p2.get(0)).getId()){
                        return 1;
                    }else{
                        return -1;
                    }
                }else if((Integer)p1.get(1)<(Integer)p2.get(1)) {
                    return -1;
                }else {
                    return 1;
                }
            }
        };
        comparator = comparator.reversed();
        Set<List<Object>> set = new TreeSet<>(comparator);
        set.addAll(list);
        return set.stream().map(pair -> (Doctor)pair.get(0)).limit(k).collect(Collectors.toList());
    }
}
