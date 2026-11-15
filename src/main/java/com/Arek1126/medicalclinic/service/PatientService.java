package com.Arek1126.medicalclinic.service;

import com.Arek1126.medicalclinic.model.Patient;
import com.Arek1126.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;


    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

   public Optional<Patient> getPatientByEmail(String email){
        return patientRepository.findPatientByEmail(email);
   }

   public boolean addPatient(Patient patient){
       return patientRepository.addPatient(patient);

   }

   public boolean deletePatientByEmail(String email){
        return patientRepository.deletePatientByEmail(email);
   }

    public boolean modifyPatient(String email, Patient patient){
        return patientRepository.modifyPatient(email, patient);
    }
}
