package com.pm.patientservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    
    @Mapping(target = "id", ignore = true)
    Patient toPatient(PatientRequestDTO entity);

    PatientResponseDTO toProductDto(Patient patient);
}
