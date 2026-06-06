package com.synergy.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "First name cannot be null")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Size(min = 1, max = 50)
    @Column(nullable = false,unique = true, length = 50)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @NotNull(message = "Birth date cannot be null")
    private LocalDate birthDate;

    @NotNull(message = "Registration date cannot be null")
    private LocalDate registrationDate;

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
