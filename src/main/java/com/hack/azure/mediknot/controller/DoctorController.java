package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.mapper.DoctorMapper;
import com.hack.azure.mediknot.service.DoctorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;
    private DoctorMapper doctorMapper;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }
}
