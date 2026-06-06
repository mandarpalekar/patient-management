package com.synergy.patientservice.dto;

import com.synergy.patientservice.model.Address;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class PatientResponseDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private AddressDTO address;

}
