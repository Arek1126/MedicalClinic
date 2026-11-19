package com.Arek1126.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class PatientNotFoundException extends MedicalClinicException {

    public PatientNotFoundException() {

        super("Patient Not Found", HttpStatus.NOT_FOUND);
    }
}
