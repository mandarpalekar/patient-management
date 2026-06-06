package com.synergy.patientservice.mapper;

import com.synergy.patientservice.dto.AddressDTO;
import com.synergy.patientservice.dto.PatientRequestDTO;
import com.synergy.patientservice.dto.PatientResponseDTO;
import com.synergy.patientservice.model.Address;
import com.synergy.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static Patient toPatient(PatientRequestDTO patientRequestDTO) {

        Address address = Address.builder()
                .street(patientRequestDTO.getAddress().getStreet())
                .city(patientRequestDTO.getAddress().getCity())
                .state(patientRequestDTO.getAddress().getState())
                .zipCode(patientRequestDTO.getAddress().getZipCode())
                .country(patientRequestDTO.getAddress().getCountry())
                .build();

        return Patient.builder()
                .firstName(patientRequestDTO.getFirstName())
                .lastName(patientRequestDTO.getLastName())
                .email(patientRequestDTO.getEmail())
                .birthDate(LocalDate.parse(patientRequestDTO.getBirthDate()))
                .registrationDate(LocalDate.now())
                .address(address)
                .build();
    }

    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {

        AddressDTO addressDTO = AddressDTO.builder()
                .street(patient.getAddress().getStreet())
                .city(patient.getAddress().getCity())
                .state(patient.getAddress().getState())
                .zipCode(patient.getAddress().getZipCode())
                .country(patient.getAddress().getCountry())
                .build();

        return PatientResponseDTO.builder()
                .id(patient.getId().toString())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .dateOfBirth(patient.getBirthDate().toString())
                .address(addressDTO)
                .build();
    }
}
