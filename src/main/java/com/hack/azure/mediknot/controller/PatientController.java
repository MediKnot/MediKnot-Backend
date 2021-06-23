package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.DoctorDto;
import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.dto.ReportDto;
import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.entity.Report;
import com.hack.azure.mediknot.mapper.PatientMapper;
import com.hack.azure.mediknot.mapper.ReportMapper;
import com.hack.azure.mediknot.service.PatientService;
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

    public PatientController(PatientService patientService, PatientMapper patientMapper, ReportMapper reportMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.reportMapper = reportMapper;
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

    @GetMapping("/share-profile/{id}")
    public String sharePatientProfile(@PathVariable Integer id, @RequestParam String email){
        patientService.shareProfile(id, email);
        return "Your profile is shared with " + email;
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
}
