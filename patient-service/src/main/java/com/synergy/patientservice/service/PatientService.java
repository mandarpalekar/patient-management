package com.synergy.patientservice.service;

import com.synergy.patientservice.dto.PatientRequestDTO;
import com.synergy.patientservice.dto.PatientResponseDTO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDTO> getAllPatients();

    PatientResponseDTO createPatient(@Valid PatientRequestDTO patientRequestDTO);

    PatientResponseDTO updatePatient(UUID patientId, @Valid PatientRequestDTO patientRequestDTO);

    void deletepatient(UUID patientId);

    PatientResponseDTO getPatientById(UUID patientId);
}
