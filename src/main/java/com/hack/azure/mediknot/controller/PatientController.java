package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.mapper.PatientMapper;
import com.hack.azure.mediknot.service.PatientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;
    private PatientMapper patientMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }
}
