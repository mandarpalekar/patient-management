package com.synergy.patientservice.controller;

import com.synergy.patientservice.dto.PatientRequestDTO;
import com.synergy.patientservice.dto.PatientResponseDTO;
import com.synergy.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patient API", description = "API for managing patients")
@RequiredArgsConstructor
@Slf4j
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieve a list of all patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        log.info("Returning {} patients", patients.size());
        return patients.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(patients);
    }

    @GetMapping(path = "/{patientId}")
    @Operation(summary = "Get patient by ID", description = "Retrieve a patient by their unique ID")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID patientId) {
        PatientResponseDTO patientResponseDTO = patientService.getPatientById(patientId);
        log.info("Returning patient with id: {}", patientId);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @PostMapping(path = "/create")
    @Operation(summary = "Create a new patient", description = "Create a new patient with the provided details")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        log.info("Created patient with name: {}", patientRequestDTO.getFullName());
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDTO);
    }

    @PutMapping(path = "/update/{patientId}")
    @Operation(summary = "Update an existing patient", description = "Update the details of an existing patient by their unique ID")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID patientId, @Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(patientId, patientRequestDTO);
        log.info("Updated patient with name: {}", patientRequestDTO.getFullName());
        return ResponseEntity.ok(patientResponseDTO);
    }

    @DeleteMapping(path = "/delete/{patientId}")
    @Operation(summary = "Delete a patient", description = "Delete a patient by their unique ID")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID patientId) {
        patientService.deletepatient(patientId);
        log.info("Deleted patient with name: {}", patientId.toString());
        return ResponseEntity.noContent().build();
    }
}
