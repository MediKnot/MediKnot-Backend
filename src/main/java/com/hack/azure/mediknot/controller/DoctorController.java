package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.DoctorDto;
import com.hack.azure.mediknot.entity.Doctor;
import com.hack.azure.mediknot.mapper.DoctorMapper;
import com.hack.azure.mediknot.service.DoctorService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;
    private DoctorMapper doctorMapper;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @PostMapping
    public EntityModel<DoctorDto> registerDoctor(@RequestBody DoctorDto doctorDto){
        Doctor doctor = doctorMapper.toEntity(doctorDto);
        doctor = doctorService.createDoctor(doctor);
        return EntityModel.of(
                doctorMapper.toDto(doctor),
                linkTo(methodOn(DoctorController.class).getDoctor(doctor.getId())).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<DoctorDto> getDoctor(@PathVariable Integer id){
        Doctor doctor = doctorService.getDoctorById(id);
        return EntityModel.of(
                doctorMapper.toDto(doctor)
        );
    }

    @PutMapping("/add-specialization/{id}")
    public EntityModel<DoctorDto> addSpecialization(@PathVariable Integer id, @RequestParam("Specialization") String specialization){
        Doctor doctor = doctorService.addSpecialization(id, specialization);
        return EntityModel.of(
                doctorMapper.toDto(doctor)
        );
    }

    @PutMapping("/add-clinic-name/{id}")
    public EntityModel<DoctorDto> addClinicName(@PathVariable Integer id, @RequestParam("ClinicName") String clinicName){
        Doctor doctor = doctorService.addClinicName(id, clinicName);
        return EntityModel.of(
                doctorMapper.toDto(doctor)
        );
    }

    @PutMapping("/add-degree/{id}")
    public EntityModel<DoctorDto> addDegree(@PathVariable Integer id, @RequestParam("Degree") String degree){
        Doctor doctor = doctorService.addDegree(id, degree);
        return EntityModel.of(
                doctorMapper.toDto(doctor)
        );
    }

    @PostMapping("/update-doctor-by-id/{id}")
    public EntityModel<DoctorDto> updateDoctorById(@PathVariable Integer id, @RequestBody DoctorDto doctorDto){
        Doctor doctor = doctorMapper.toEntity(doctorDto);
        Doctor doctorUpdated = doctorService.updateDoctorById(id, doctor);
        return EntityModel.of(
                doctorMapper.toDto(doctorUpdated)
        );
    }
}
