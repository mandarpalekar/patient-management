package com.synergy.patientservice.mapper;

import com.synergy.patientservice.dto.PatientRequestDTO;
import com.synergy.patientservice.dto.PatientResponseDTO;
import com.synergy.patientservice.model.Patient;
import org.mapstruct.*;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "birthDate", expression = "java(java.time.LocalDate.parse(dto.getBirthDate()))")
    Patient toPatient(PatientRequestDTO dto);

    @Mapping(target = "dateOfBirth", expression = "java(patient.getBirthDate().toString())")
    @Mapping(target = "id", expression = "java(patient.getId().toString())")
    PatientResponseDTO toPatientResponseDTO(Patient patient);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "birthDate", expression = "java(java.time.LocalDate.parse(dto.getBirthDate()))")
    void updatePatientFromDTO(PatientRequestDTO dto, @MappingTarget Patient patient);
}