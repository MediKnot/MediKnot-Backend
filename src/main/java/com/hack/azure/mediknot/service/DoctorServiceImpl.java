package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.config.BeanNotNullCopy;
import com.hack.azure.mediknot.entity.Disease;
import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.exception.DoctorException;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.repository.DoctorRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    @Value("${radius}")
    private Float radius;

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
    public List<Doctor> getDoctorsByFilter(Float lat, Float lon, String specialisation, Integer radius) {
        List<Doctor> allDoctors = getAllDoctors();

        if(radius!=null){
            this.radius = Float.valueOf(radius);
        }

        List<Double> distances = new ArrayList<>();

        List<Doctor> nearByDoctors = new ArrayList<>();
        List<Doctor> resultDoctors;

        for(Doctor doctor:allDoctors){

            if(doctor.getAddress()==null||doctor.getAddress().getLatitude()==null||doctor.getAddress().getLongitude()==null)
                continue;

            Double distance = calc_distance(lat, lon, doctor.getAddress().getLatitude(), doctor.getAddress().getLongitude(), 'K');

            if(distance <= this.radius){
                nearByDoctors.add(doctor);
                distances.add(distance);
            }
        }
        nearByDoctors.sort(Comparator.comparingDouble(distances::indexOf));



        if(specialisation!=null){
            resultDoctors = new ArrayList<>();
            for(Doctor doctor:nearByDoctors){
                int partialRatio = 0;

                for(String val:doctor.getSpecialization()){
                    int currRatio = FuzzySearch.partialRatio(val, specialisation);
                    partialRatio = Math.max(partialRatio, currRatio);
                }

                if(partialRatio>=70){
                    resultDoctors.add(doctor);
                }
            }
        }else {
            resultDoctors = nearByDoctors;
        }

        return resultDoctors.stream().limit(10).collect(Collectors.toList());
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

    private double calc_distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
