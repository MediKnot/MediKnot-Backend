package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.DiseaseDto;
import com.hack.azure.mediknot.dto.MedicineDto;
import com.hack.azure.mediknot.entity.Medicine;
import com.hack.azure.mediknot.mapper.MedicineMapper;
import com.hack.azure.mediknot.service.MedicineService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private MedicineService medicineService;
    private MedicineMapper medicineMapper;

    public MedicineController(MedicineService medicineService, MedicineMapper medicineMapper) {
        this.medicineService = medicineService;
        this.medicineMapper = medicineMapper;
    }

    @PostMapping
    public EntityModel<MedicineDto> addMedicine(@RequestBody MedicineDto medicineDto){
        Medicine medicine = medicineMapper.toEntity(medicineDto);
        medicine = medicineService.addMedicine(medicine);
        return EntityModel.of(
                medicineMapper.toDto(medicine),
                linkTo(methodOn(MedicineController.class).getMedicine(medicine.getId())).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<MedicineDto> getMedicine(@PathVariable Integer id){
        return EntityModel.of(
                medicineMapper.toDto(medicineService.getMedicine(id))
        );
    }

    @GetMapping("/search")
    public CollectionModel<EntityModel<MedicineDto>> searchMedicine(@RequestParam String name){
        List<EntityModel<MedicineDto>> result = medicineService.searchMedicine(name).stream().map(
                disease -> EntityModel.of(medicineMapper.toDto(disease))
        ).collect(Collectors.toList());
        return CollectionModel.of(
                result
        );
    }
}
