package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.MedicalEventDto;
import com.hack.azure.mediknot.dto.ReportDto;
import com.hack.azure.mediknot.entity.MedicalEvent;
import com.hack.azure.mediknot.entity.Report;
import com.hack.azure.mediknot.mapper.MedicalEventMapper;
import com.hack.azure.mediknot.mapper.ReportMapper;
import com.hack.azure.mediknot.service.MedicalEventService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/medicalEvent")
public class MedicalEventController {

    private MedicalEventService medicalEventService;
    private MedicalEventMapper medicalEventMapper;
    private ReportMapper reportMapper;

    public MedicalEventController(MedicalEventService medicalEventService, MedicalEventMapper medicalEventMapper, ReportMapper reportMapper) {
        this.medicalEventService = medicalEventService;
        this.medicalEventMapper = medicalEventMapper;
        this.reportMapper = reportMapper;
    }

    @PostMapping("/{patientId}")
    public EntityModel<MedicalEventDto> addMedicalEvent(@PathVariable Integer patientId, @RequestBody MedicalEventDto medicalEventDto){
        MedicalEvent medicalEvent = medicalEventMapper.toEntity(medicalEventDto);
        medicalEvent = medicalEventService.addMedicalEvent(patientId, medicalEvent);
        return EntityModel.of(
                medicalEventMapper.toDto(medicalEvent),
                linkTo(methodOn(MedicalEventController.class).getMedicalEvent(medicalEvent.getId())).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<MedicalEventDto> getMedicalEvent(@PathVariable Integer id){
        MedicalEvent medicalEvent = medicalEventService.getMedicalEvent(id);
        return EntityModel.of(
                medicalEventMapper.toDto(medicalEvent)
        );
    }

    @DeleteMapping("/{id}")
    public String removeMedicalEvent(@PathVariable Integer id){
        medicalEventService.removeMedicalEvent(id);
        return "Medical Event removed with id: " + id;
    }

    @PutMapping("/{id}")
    public EntityModel<MedicalEventDto> updateMedicalEventById(@PathVariable Integer id, @RequestBody MedicalEventDto medicalEventDto){
        MedicalEvent medicalEvent = medicalEventMapper.toEntity(medicalEventDto);
        MedicalEvent medicalEventUpdated = medicalEventService.updateMedicalEventById(id, medicalEvent);
        return EntityModel.of(
                medicalEventMapper.toDto(medicalEventUpdated)
        );
    }
    
    @GetMapping("/close-medical-event/{id}")
    public EntityModel<MedicalEventDto> closeMedicalEvent(@PathVariable Integer id){
        MedicalEvent medicalEvent = medicalEventService.closeMedicalEvent(id);
        return EntityModel.of(
                medicalEventMapper.toDto(medicalEvent)
        );
    }


    @PutMapping("/add/reports/{id}")
    public EntityModel<MedicalEventDto> addReports(@PathVariable Integer id, @RequestBody List<ReportDto> reportDtos){
        List<Report> reportList = reportDtos.stream().map(reportDto -> reportMapper.toEntity(reportDto))
                .collect(Collectors.toList());
        MedicalEvent medicalEvent = medicalEventService.addReports(id, reportList);
        return EntityModel.of(
                medicalEventMapper.toDto(medicalEvent)
        );
    }

    @PutMapping("/add/disease/{eventId}/{diseaseId}")
    public EntityModel<MedicalEventDto> addDisease(@PathVariable Integer eventId, @PathVariable Integer diseaseId){
        MedicalEvent medicalEvent = medicalEventService.addDisease(eventId, diseaseId);
        return EntityModel.of(
                medicalEventMapper.toDto(medicalEvent)
        );
    }

    @PutMapping("/remove/disease/{eventId}/{diseaseId}")
    public EntityModel<MedicalEventDto> removeDisease(@PathVariable Integer eventId, @PathVariable Integer diseaseId){
        MedicalEvent medicalEvent = medicalEventService.removeDisease(eventId, diseaseId);
        return EntityModel.of(
                medicalEventMapper.toDto(medicalEvent)
        );
    }

    @PutMapping("/clear/reports/{id}")
    public String clearReports(@PathVariable Integer id){
        medicalEventService.clearReports(id);
        return "Reports cleared of medicalEvent with id: " + id.toString();
    }
}
