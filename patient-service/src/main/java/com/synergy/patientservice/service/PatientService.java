package com.synergy.patientservice.service;

import com.synergy.patientservice.dto.PatientRequestDTO;
import com.synergy.patientservice.dto.PatientResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PatientService {
    List<PatientResponseDTO> getAllPatients();

    PatientResponseDTO createPatient(@Valid PatientRequestDTO patientRequestDTO);

    PatientResponseDTO updatePatient(@Valid PatientRequestDTO patientRequestDTO);
}
