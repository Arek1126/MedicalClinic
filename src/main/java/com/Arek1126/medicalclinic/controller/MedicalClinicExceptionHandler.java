package com.Arek1126.medicalclinic.controller;

import com.Arek1126.medicalclinic.exception.MedicalClinicException;
import com.Arek1126.medicalclinic.model.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MedicalClinicExceptionHandler {

    @ExceptionHandler(MedicalClinicException.class)
    ResponseEntity<ErrorMessage> handleMedicalClinicException(MedicalClinicException ex){
        return ResponseEntity.status(ex.getStatus()).body(new ErrorMessage(ex.getMessage()));
    }


}
