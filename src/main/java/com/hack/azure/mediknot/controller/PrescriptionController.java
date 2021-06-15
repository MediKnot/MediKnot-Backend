package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.DosageDto;
import com.hack.azure.mediknot.dto.PrescriptionDto;
import com.hack.azure.mediknot.entity.Dosage;
import com.hack.azure.mediknot.entity.Prescription;
import com.hack.azure.mediknot.mapper.DosageMapper;
import com.hack.azure.mediknot.mapper.PrescriptionMapper;
import com.hack.azure.mediknot.service.PrescriptionService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    private PrescriptionService prescriptionService;
    private PrescriptionMapper prescriptionMapper;
    private DosageMapper dosageMapper;

    public PrescriptionController(PrescriptionService prescriptionService, PrescriptionMapper prescriptionMapper, DosageMapper dosageMapper) {
        this.prescriptionService = prescriptionService;
        this.prescriptionMapper = prescriptionMapper;
        this.dosageMapper = dosageMapper;
    }

    @PostMapping("/{consultationId}")
    public EntityModel<PrescriptionDto> addPrescription(@PathVariable Integer consultationId, @RequestBody PrescriptionDto prescriptionDto){
        Prescription prescription = prescriptionMapper.toEntity(prescriptionDto);
        prescription = prescriptionService.addPrescription(consultationId, prescription);
        return EntityModel.of(
                prescriptionMapper.toDto(prescription),
                linkTo(methodOn(PrescriptionController.class).getPrescriptionById(prescription.getId())).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<PrescriptionDto> getPrescriptionById(@PathVariable Integer id){
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        return EntityModel.of(
                prescriptionMapper.toDto(prescription)
        );
    }

    @DeleteMapping("/{id}")
    public EntityModel<String> removePrescription(@PathVariable Integer id){
        prescriptionService.removePrescription(id);
        return EntityModel.of(
                "Prescription removed with id: " + id,
                linkTo(methodOn(PrescriptionController.class).getPrescriptionById(id)).withSelfRel()
        );
    }

    @PutMapping("/{id}")
    public EntityModel<PrescriptionDto> updatePrescriptionById(@PathVariable Integer id, @RequestBody PrescriptionDto prescriptionDto){
        Prescription prescription = prescriptionMapper.toEntity(prescriptionDto);
        Prescription prescriptionUpdated = prescriptionService.updatePrescriptionById(id, prescription);
        return EntityModel.of(
                prescriptionMapper.toDto(prescriptionUpdated)
        );
    }

    @PutMapping("/add/dosages/{id}")
    public EntityModel<PrescriptionDto> addDosages(@PathVariable Integer id, @RequestBody List<DosageDto> dosageDtos){
        List<Dosage> dosageList = dosageDtos.stream().map(dosageDto -> dosageMapper.toEntity(dosageDto))
                .collect(Collectors.toList());
        Prescription prescription = prescriptionService.addDosages(id, dosageList);
        return EntityModel.of(
                prescriptionMapper.toDto(prescription)
        );
    }

    @PutMapping("/clear/dosages/{id}")
    public EntityModel<String> clearDosages(@PathVariable Integer id){
        prescriptionService.clearDosages(id);
        return EntityModel.of(
                "Dosages cleared of Prescription with id: " + id,
                linkTo(methodOn(PrescriptionController.class).getPrescriptionById(id)).withSelfRel()
        );
    }
}
