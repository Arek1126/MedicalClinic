package com.Arek1126.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class PatientAlreadyExistsException extends MedicalClinicException{

    public PatientAlreadyExistsException(){
        super("Patient Already Exists", HttpStatus.CONFLICT);
    }
}
