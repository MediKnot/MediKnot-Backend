package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.dto.ProfileViewsDto;
import com.hack.azure.mediknot.dto.ReportDto;
import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.ProfileViews;
import com.hack.azure.mediknot.entity.Report;
import com.hack.azure.mediknot.mapper.PatientMapper;
import com.hack.azure.mediknot.mapper.ProfileViewsMapper;
import com.hack.azure.mediknot.mapper.ReportMapper;
import com.hack.azure.mediknot.service.PatientService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;
    private PatientMapper patientMapper;
    private ReportMapper reportMapper;
    private ProfileViewsMapper profileViewsMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper, ReportMapper reportMapper, ProfileViewsMapper profileViewsMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.reportMapper = reportMapper;
        this.profileViewsMapper = profileViewsMapper;
    }

    @PostMapping
    public EntityModel<PatientDto> createPatient(@RequestBody PatientDto patientDto){
        Patient patient = patientMapper.toEntity(patientDto);
        patient = patientService.createPatient(patient);
        return EntityModel.of(
                patientMapper.toDto(patient),
                linkTo(methodOn(PatientController.class).getPatient(patient.getId())).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<PatientDto> getPatient(@PathVariable Integer id){
        Patient patient = patientService.getPatientById(id);
        return EntityModel.of(
                patientMapper.toDto(patient)
        );
    }

    @GetMapping("/phone/{number}")
    public EntityModel<PatientDto> getPatient(@PathVariable String number){
        Patient patient = patientService.getPatientByPhoneNumber(number);
        return EntityModel.of(
                patientMapper.toDto(patient)
        );
    }

    @PutMapping("/add/reports/{id}")
    public EntityModel<PatientDto> addReports(@PathVariable Integer id, @RequestBody List<ReportDto> reportDtos){
        List<Report> reportList = reportDtos.stream().map(reportDto -> reportMapper.toEntity(reportDto))
                .collect(Collectors.toList());
        Patient patient = patientService.addReports(id, reportList);
        return EntityModel.of(
                patientMapper.toDto(patient)
        );
    }

    @PutMapping("/add/allergies/{id}")
    public EntityModel<PatientDto> addAllergies(@PathVariable Integer id, @RequestBody List<String> allergies){
        Patient patient = patientService.addAllergies(id, allergies);
        return EntityModel.of(
                patientMapper.toDto(patient)
        );
    }

    @PutMapping("/clear/reports/{id}")
    public String clearReports(@PathVariable Integer id){
        patientService.clearReports(id);
        return "Reports cleared of patient with id: " + id.toString();
    }

    @PutMapping("/{id}")
    public EntityModel<PatientDto> updatePatientById(@PathVariable Integer id, @RequestBody PatientDto patientDto){
        Patient patient = patientMapper.toEntity(patientDto);
        patient = patientService.updatePatientById(id, patient);
        return EntityModel.of(
                patientMapper.toDto(patient)
        );
    }

    @GetMapping("/share-profile/{id}")
    public String shareProfile(@PathVariable Integer id, @RequestParam String name, @RequestParam String emailId){
        patientService.sharePatientProfile(id, name, emailId);
        return "Shared profile with person having email id: " + emailId;
    }

    @GetMapping("/views/add/{id}/{name}/{email}")
    public EntityModel<ProfileViewsDto> addView(@PathVariable Integer id, @PathVariable String name, @PathVariable String email){
        ProfileViews profileViews = patientService.addView(id, name, email);
        return EntityModel.of(
                profileViewsMapper.toDto(profileViews)
        );
    }

    @GetMapping("/views/{id}")
    public CollectionModel<EntityModel<ProfileViewsDto>> getAllViews(@PathVariable Integer id){
        List<ProfileViews> profileViews = patientService.getAllViews(id);
        List<EntityModel<ProfileViewsDto>> entityModels = profileViews.stream().map(profileViews1 -> EntityModel.of(
                profileViewsMapper.toDto(profileViews1)
        )).collect(Collectors.toList());

        return CollectionModel.of(
                entityModels
        );
    }
}
