package com.pm.patientservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;

@RestController
@RequestMapping("patients")
public class PatientController {
    
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        return ResponseEntity.ok().body(patientService.getPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO dto){
        return ResponseEntity.ok().body(patientService.createPatient(dto));
    }
}
