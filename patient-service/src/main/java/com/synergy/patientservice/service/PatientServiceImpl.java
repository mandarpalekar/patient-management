package com.synergy.patientservice.service;

import com.synergy.patientservice.dto.PatientRequestDTO;
import com.synergy.patientservice.dto.PatientResponseDTO;
import com.synergy.patientservice.exception.EmailAlreadyExistsException;
import com.synergy.patientservice.exception.PatientNotExistException;
import com.synergy.patientservice.mapper.PatientMapper;
import com.synergy.patientservice.model.Patient;
import com.synergy.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        log.info("Found {} patients", patients.size());
        return patients.stream().map(patientMapper::toPatientResponseDTO).toList();
    }

    @Override
    public PatientResponseDTO getPatientById(UUID patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotExistException(
                        "A patient with id " + patientId + " does not exist"));
        log.info("Returning patient with id: {}", patientId);
        return patientMapper.toPatientResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A Patient with this email " + patientRequestDTO.getEmail() +
                    " already exists");
        }
        Patient patient = patientRepository.save(patientMapper.toPatient(patientRequestDTO));
        log.info("Saved patient with name: {}", patient.getFullName());
        return patientMapper.toPatientResponseDTO(patient);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID patientId, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotExistException(
                        "A patient with id " + patientId + " does not exist"));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), patientId)) {
            throw new EmailAlreadyExistsException("A Patient with this email " + patientRequestDTO.getEmail() +
                    " already exists");
        }
        patientMapper.updatePatientFromDTO(patientRequestDTO, patient); // updates existing patient
        Patient updatedPatient = patientRepository.save(patient);
        log.info("Updated patient with name: {}", updatedPatient.getFullName());
        return patientMapper.toPatientResponseDTO(updatedPatient);
    }

    @Override
    public void deletepatient(UUID patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotExistException(
                        "A patient with id " + patientId + " does not exist"));
        patientRepository.delete(patient);
        log.info("Deleted patient with name: {}", patient.getFullName());
    }
}
