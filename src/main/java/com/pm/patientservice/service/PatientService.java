package com.pm.patientservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
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
        if (patientRepository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + dto.email());
        }
        Patient patient = patientMapper.toPatient(dto);

        patient = patientRepository.save(patient);
        
        return patientMapper.toProductDto(patient);
    }
}
