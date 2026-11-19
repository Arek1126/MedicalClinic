package com.Arek1126.medicalclinic.service;

import com.Arek1126.medicalclinic.exception.PatientAlreadyExists;
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

   public Patient getByEmail(String email){
        return patientRepository.findByEmail(email)
                .orElseThrow(PatientNotFoundException::new);
   }

   public Patient addPatient(Patient patient){
        return patientRepository.addPatient(patient)
                .orElseThrow(PatientAlreadyExists::new);
   }

   public void deleteByEmail(String email){
        if(!patientRepository.deleteByEmail(email)){
            throw new PatientNotFoundException();
        }
   }

    public Patient modifyPatient(String email, Patient patient){
        return patientRepository.modifyByEmail(email, patient)
                .orElseThrow(PatientNotFoundException::new);
    }

    public void changePassword(String email, String newPassword){
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(PatientNotFoundException::new);
        patient.setPassword(newPassword);
    }
}
