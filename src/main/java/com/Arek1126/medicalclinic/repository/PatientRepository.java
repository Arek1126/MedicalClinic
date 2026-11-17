package com.Arek1126.medicalclinic.repository;

import com.Arek1126.medicalclinic.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public List<Patient> findAll(){
        return new ArrayList<>(patients);
    }

    public Optional<Patient> findByEmail(String email){
        return patients.stream()
                .filter(patient -> patient.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<Patient> addPatient(Patient newPatient){
        return findByEmail(newPatient.getEmail())
                .map(patient -> Optional.<Patient>empty())
                .orElseGet(() -> {
                    patients.add(newPatient);
                    return Optional.of(newPatient);
                });
    }

    public boolean deleteByEmail(String email){
        return findByEmail(email)
                .map(patients::remove)
                .orElse(false);
    }

    public Optional<Patient> modifyByEmail(String email, Patient patient){
        return findByEmail(email)
                .map(oldPatient -> {
                    oldPatient.setFirstName(patient.getFirstName());
                    oldPatient.setLastName(patient.getLastName());
                    oldPatient.setBirthday(patient.getBirthday());
                    oldPatient.setPhoneNumber(patient.getPhoneNumber());
                    oldPatient.setIdCardNumber(patient.getIdCardNumber());
                    return Optional.of(patient);
                })
                .orElseGet(Optional::empty);
    }
}