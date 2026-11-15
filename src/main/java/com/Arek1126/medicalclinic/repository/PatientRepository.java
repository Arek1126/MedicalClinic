package com.Arek1126.medicalclinic.repository;

import com.Arek1126.medicalclinic.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepository {

    private final List<Patient> patients;

    public PatientRepository() {
        this.patients = new ArrayList<>();
    }

    public List<Patient> findAll(){
        return new ArrayList<>(patients);
    }

    public Optional<Patient> findPatientByEmail(String email){
        return patients.stream().filter(p -> p.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public boolean addPatient(Patient patient){
        if(findPatientByEmail(patient.getEmail()).isPresent())
            return false;
        else {
            patients.add(patient);
            return true;
        }
    }

    public boolean deletePatientByEmail(String email){
        return patients.removeIf(p -> p.getEmail().equalsIgnoreCase(email));
    }

    public boolean modifyPatient(String email, Patient patient){
        if(patients.removeIf(p -> p.getEmail().equalsIgnoreCase(email)))
            return patients.add(patient);
        return false;
    }
}
