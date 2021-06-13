package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.dto.ReportDto;
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

    @PutMapping("/add-reports/{id}")
    public EntityModel<PatientDto> addReports(@PathVariable Integer id, @RequestBody List<ReportDto> reportDtos){
        List<Report> reportList = reportDtos.stream().map(reportDto -> reportMapper.toEntity(reportDto))
                .collect(Collectors.toList());
        Patient patient = patientService.addReports(id, reportList);
        return EntityModel.of(
                patientMapper.toDto(patient)
        );
    }

    @GetMapping("/clear-reports/{id}")
    public EntityModel<String> clearReports(@PathVariable Integer id){
        patientService.clearReports(id);
        return EntityModel.of(
                "Reports cleared of patient with id: " + id,
                linkTo(methodOn(PatientController.class).getPatient(id)).withSelfRel()
        );
    }
}
