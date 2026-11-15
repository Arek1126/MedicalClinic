package com.Arek1126.medicalclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Patient {

    private String email;
    private String password;
    private String idCardNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthday;

}
