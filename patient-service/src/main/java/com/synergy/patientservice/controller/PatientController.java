package com.synergy.patientservice.controller;

import com.synergy.patientservice.dto.PatientRequestDTO;
import com.synergy.patientservice.dto.PatientResponseDTO;
import com.synergy.patientservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Slf4j
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        log.info("Returning {} patients", patients.size());
        return patients.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(patients);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        log.info("Created patient with name: {}", patientRequestDTO.getFullName());
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDTO);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(patientRequestDTO);
        log.info("Updated patient with name: {}", patientRequestDTO.getFullName());
        return ResponseEntity.ok(patientResponseDTO);
    }
}
