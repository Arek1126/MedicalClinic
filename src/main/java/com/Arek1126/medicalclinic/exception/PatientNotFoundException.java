package com.Arek1126.medicalclinic.exception;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(){
        super(" Błąd: taki użytkownik nie istnieje");
    }
}
