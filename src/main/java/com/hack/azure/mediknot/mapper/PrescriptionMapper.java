package com.hack.azure.mediknot.mapper;


import com.hack.azure.mediknot.dto.MedicineDto;
import com.hack.azure.mediknot.dto.PrescriptionDto;
import com.hack.azure.mediknot.entity.Medicine;
import com.hack.azure.mediknot.entity.Prescription;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface PrescriptionMapper {

    Prescription toEntity(PrescriptionDto prescriptionDto);

    PrescriptionDto toDto(Prescription prescription);

    default MedicineDto medicineToDto(Medicine medicine){
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setId(medicine.getId());
        medicineDto.setMedicineName(medicine.getMedicineName());
        medicineDto.setMedicineFullName(medicine.getMedicineFullName());
        return medicineDto;
    }

}