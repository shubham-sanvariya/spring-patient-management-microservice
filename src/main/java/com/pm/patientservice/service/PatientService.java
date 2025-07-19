package com.pm.patientservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientResponseDTO> getPatients() {
        return patientRepository
                .findAll()
                .stream()
                .map(p -> patientMapper.toProductDto(p))
                .toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO dto){
        Patient patient = patientMapper.toPatient(dto);

        patient.setRegisteredDate(LocalDate.now());

        patient = patientRepository.save(patient);
        
        return patientMapper.toProductDto(patient);
    }
}
