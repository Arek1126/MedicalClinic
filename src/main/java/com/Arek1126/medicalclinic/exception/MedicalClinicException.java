package com.Arek1126.medicalclinic.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class MedicalClinicException extends RuntimeException{

    private HttpStatus status;

    public MedicalClinicException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
