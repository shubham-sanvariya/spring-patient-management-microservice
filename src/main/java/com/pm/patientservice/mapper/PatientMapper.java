package com.pm.patientservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.pm.patientservice.dto.PatientCreateDTO;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    
    @Mapping(target = "id", ignore = true)
    Patient toPatient(PatientCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registeredDate", ignore = true)
    Patient toPatient(PatientRequestDTO dto, @MappingTarget Patient patient);

    PatientResponseDTO toProductDto(Patient patient);
}
