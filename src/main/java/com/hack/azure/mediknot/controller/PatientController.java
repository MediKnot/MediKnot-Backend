package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.PatientDto;
import com.hack.azure.mediknot.entity.Patient;
import com.hack.azure.mediknot.mapper.PatientMapper;
import com.hack.azure.mediknot.service.PatientService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;
    private PatientMapper patientMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
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
}
