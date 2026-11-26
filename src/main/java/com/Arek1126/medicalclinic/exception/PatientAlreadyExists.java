package com.Arek1126.medicalclinic.exception;

import org.springframework.http.HttpStatus;

public class PatientAlreadyExists extends MedicalClinicException{

    public PatientAlreadyExists(){
        super("Patient Already Exists", HttpStatus.CONFLICT);
    }
}
