package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.DiseaseDto;
import com.hack.azure.mediknot.entity.Disease;
import com.hack.azure.mediknot.mapper.DiseaseMapper;
import com.hack.azure.mediknot.service.DiseaseService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

    private DiseaseService diseaseService;
    private DiseaseMapper diseaseMapper;

    public DiseaseController(DiseaseService diseaseService, DiseaseMapper diseaseMapper) {
        this.diseaseService = diseaseService;
        this.diseaseMapper = diseaseMapper;
    }

    @PostMapping
    public EntityModel<DiseaseDto> addDisease(@RequestBody DiseaseDto diseaseDto){
        Disease disease = diseaseMapper.toEntity(diseaseDto);
        disease = diseaseService.addDisease(disease);
        return EntityModel.of(
                diseaseMapper.toDto(disease),
                linkTo(methodOn(DiseaseController.class).getDisease(disease.getId())).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<DiseaseDto> getDisease(@PathVariable Integer id){
        Disease disease = diseaseService.getDisease(id);
        return EntityModel.of(
                diseaseMapper.toDto(disease)
        );
    }

    @GetMapping("/search")
    public CollectionModel<EntityModel<DiseaseDto>> searchDiseases(@RequestParam String name){
        List<EntityModel<DiseaseDto>> result = diseaseService.searchDiseases(name).stream().map(
                disease -> EntityModel.of(diseaseMapper.toDto(disease))
        ).collect(Collectors.toList());
        return CollectionModel.of(
                result
        );
    }
}
