package com.pm.patientservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pm.patientservice.dto.PatientCreateDTO;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
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

    public void emailExists(String email) {
        if (patientRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + email);
        }
    }

    public PatientResponseDTO createPatient(PatientCreateDTO dto){
        emailExists(dto.email());
        Patient patient = patientMapper.toPatient(dto);

        patient = patientRepository.save(patient);
        
        return patientMapper.toProductDto(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO dto){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        // it checks that the user email exists for other than user id
        if (patientRepository.existsByEmailAndIdNot(dto.email(),id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + dto.email());
        }

        patient = patientMapper.toPatient(dto,patient);

        return patientMapper.toProductDto(patientRepository.save(patient));
    }

    public void deletePatient(UUID id){
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient not found with ID: " + id);
        }

        patientRepository.deleteById(id);
    }

}
