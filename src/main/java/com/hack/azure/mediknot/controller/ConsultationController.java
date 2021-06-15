package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.*;
import com.hack.azure.mediknot.entity.Consultation;
import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Report;
import com.hack.azure.mediknot.entity.Treatment;
import com.hack.azure.mediknot.mapper.ConsultationMapper;
import com.hack.azure.mediknot.mapper.TreatmentMapper;
import com.hack.azure.mediknot.service.ConsultationService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    private ConsultationService consultationService;
    private ConsultationMapper consultationMapper;
    private TreatmentMapper treatmentMapper;

    public ConsultationController(ConsultationService consultationService, ConsultationMapper consultationMapper, TreatmentMapper treatmentMapper) {
        this.consultationService = consultationService;
        this.consultationMapper = consultationMapper;
        this.treatmentMapper = treatmentMapper;
    }

    @PostMapping("/{doctorId}")
    public EntityModel<ConsultationDto> addConsultation(@PathVariable Integer doctorId, @RequestBody ConsultationDto consultationDto){
        Consultation consultation = consultationMapper.toEntity(consultationDto);
        consultation = consultationService.addConsultation(doctorId, consultation);
        return EntityModel.of(
                consultationMapper.toDto(consultation),
                linkTo(methodOn(ConsultationController.class).getConsultation(consultation.getId())).withSelfRel()
        );
    }

    @PostMapping("event/{eventId}/{doctorId}")
    public EntityModel<ConsultationDto> addConsultation(@PathVariable Integer eventId, @PathVariable Integer doctorId, @RequestBody ConsultationDto consultationDto){
        Consultation consultation = consultationMapper.toEntity(consultationDto);
        consultation = consultationService.addConsultation(eventId, doctorId, consultation);
        return EntityModel.of(
                consultationMapper.toDto(consultation),
                linkTo(methodOn(ConsultationController.class).getConsultation(consultation.getId())).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<ConsultationDto> getConsultation(@PathVariable Integer id){
        Consultation consultation = consultationService.getConsultation(id);
        return EntityModel.of(
                consultationMapper.toDto(consultation)
        );
    }

    @DeleteMapping("/{id}")
    public EntityModel<String> deleteConsultation(@PathVariable Integer id){
        consultationService.removeConsultation(id);
        return EntityModel.of(
                "Consultation removed with id: " + id
        );
    }

    @PutMapping("/add/notes/{id}")
    public EntityModel<ConsultationDto> addNotes(@PathVariable Integer id, @RequestBody List<String> notes){
        Consultation consultation = consultationService.addNotes(id, notes);
        return EntityModel.of(
                consultationMapper.toDto(consultation)
        );
    }

    @GetMapping("/clear/reports/{id}")
    public EntityModel<String> clearNotes(@PathVariable Integer id){
        consultationService.clearNotes(id);
        return EntityModel.of(
                "Reports cleared of consultation with id: " + id
        );
    }

    @PutMapping("/add/treatment/{id}")
    public EntityModel<ConsultationDto> addTreatments(@PathVariable Integer id, @RequestBody List<TreatmentDto> treatmentDtos){
        List<Treatment> treatmentList = treatmentDtos.stream().map(treatmentDto -> treatmentMapper.toEntity(treatmentDto))
                .collect(Collectors.toList());
        Consultation consultation = consultationService.addTreatment(id, treatmentList);
        return EntityModel.of(
                consultationMapper.toDto(consultation)
        );
    }

    @GetMapping("/clear/treatment/{id}")
    public EntityModel<String> clearTreatments(@PathVariable Integer id){
        consultationService.clearTreatment(id);
        return EntityModel.of(
                "Treatments cleared of consultation with id: " + id
        );
    }
}
