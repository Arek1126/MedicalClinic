package com.Arek1126.medicalclinic.service;

import com.Arek1126.medicalclinic.exception.PatientNotFoundException;
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

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

   public Optional<Patient> getByEmail(String email){
        return patientRepository.findByEmail(email);
   }

   public Optional<Patient> addPatient(Patient patient){
       return patientRepository.addPatient(patient);
   }

   public boolean deleteByEmail(String email){
        return patientRepository.deleteByEmail(email);
   }

    public Optional<Patient> modifyPatient(String email, Patient patient){
        return patientRepository.modifyByEmail(email, patient);
    }

    public void changePassword(String email, String newPassword){
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(PatientNotFoundException::new);
        patient.setPassword(newPassword);
    }
}
