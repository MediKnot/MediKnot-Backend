package com.hack.azure.mediknot.service;

import com.hack.azure.mediknot.config.BeanNotNullCopy;
import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.ProfileViews;
import com.hack.azure.mediknot.entity.Report;
import com.hack.azure.mediknot.exception.PatientException;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.repository.PatientRepository;
import com.hack.azure.mediknot.repository.ProfileViewsRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    private PatientRepository patientRepository;
    private ProfileViewsRepository profileViewsRepository;
    private EmailService emailService;

    public PatientServiceImpl(PatientRepository patientRepository, ProfileViewsRepository profileViewsRepository, EmailService emailService) {
        this.patientRepository = patientRepository;
        this.profileViewsRepository = profileViewsRepository;
        this.emailService = emailService;
    }

    @Override
    public Patient getPatientById(Integer id) throws PatientException{
        return patientRepository.findById(id).orElseThrow(() -> new PatientException("Patient not found.", 404));
    }

    @Override
    public Patient updatePatientById(Integer id, Patient patient) throws PatientException{
        Patient existingPatient;
        try{
            existingPatient = getPatientById(id);
        }catch (UserException e){
            throw new UserException("Couldn't update, patient not found", 404);
        }
        BeanNotNullCopy.copyNonNullProperties(patient,existingPatient);
        return patientRepository.save(existingPatient);
    }

    @Override
    public Patient createPatient(Patient patient) throws PatientException {
        if(patientRepository.existsByEmailId(patient.getEmailId())){
            throw new PatientException("Patient with email id already exists", 409);
        }
        try{
            patient = patientRepository.save(patient);
        }catch (DataIntegrityViolationException exception){
            throw new PatientException("User with phone number already exists.", 409);
        }
        return patient;
    }

    @Override
    public Patient addReports(Integer id, List<Report> reports) throws PatientException{
        Patient patient = getPatientById(id);
        if(patient.getGeneralReports() == null){
            patient.setGeneralReports(new ArrayList<>());
        }
        patient.getGeneralReports().addAll(reports);
        return updatePatient(patient);
    }

    @Override
    public Patient addAllergies(Integer id, List<String> allergies) throws PatientException{
        Patient patient = getPatientById(id);
        if(patient.getAllergies() == null){
            patient.setAllergies(new ArrayList<>());
        }
        patient.getAllergies().addAll(allergies);
        return updatePatient(patient);
    }

    @Override
    public void clearReports(Integer id) throws PatientException{
        Patient patient = getPatientById(id);
        if(patient.getGeneralReports()==null && patient.getGeneralReports().isEmpty()){
            throw new PatientException("Reports are not present of Patient", 204);
        }
        patient.getGeneralReports().clear();
        updatePatient(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) throws UserException {
        if(patient.getId()==null){
            throw new UserException("Id not present in Patient body", 204);
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientByPhoneNumber(String phoneNumber) {
        Patient patient = patientRepository.findByPhoneNumber(phoneNumber);
        if(patient==null)
            throw new PatientException("Patient not found with phone number", 404);
        return patient;
    }

    @Override
    public void sharePatientProfile(Integer id, String name, String emailId) {
        Patient patient = getPatientById(id);
        String subject = "Patient - " + patient.getName() + " has shared profile!";
        String link1 = null, link2 = null;
        try {
            link1 = "http://20.198.81.29/view-profile?patientId=" + id + "&name=" + URLEncoder.encode(name, String.valueOf(StandardCharsets.UTF_8)) + "&email=" + emailId;
            link2 = "http://20.198.81.29/view-profile?patientId=" + id + "&name=" + URLEncoder.encode(name, String.valueOf(StandardCharsets.UTF_8)) + "&email=" + emailId;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String body = "Dear "+ name + ", \nHope you are safe and fine! \nYou are doing great work \n\nPatient - "+ patient.getName() + " has shared profile, please have a close look by clicking on the link below \n" + link1 + "\n" + link2 + "\nThanks and Regards, \nMediKnot";
        emailService.sendMail(emailId, body, subject);
    }

    @Override
    public ProfileViews addView(Integer id, String name, String email) {
        Patient patient = getPatientById(id);
        ProfileViews profileViews = new ProfileViews();
        profileViews.setPatient(patient);
        profileViews.setViewersName(name);
        profileViews.setViewersEmail(email);
        profileViews.setTimestamp(LocalDateTime.now());
        return profileViewsRepository.save(profileViews);
    }

    @Override
    public List<ProfileViews> getAllViews(Integer id) {
        Patient patient = getPatientById(id);
        return profileViewsRepository.findAllByPatient(patient);
    }
}
