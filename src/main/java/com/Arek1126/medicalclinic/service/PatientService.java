package com.Arek1126.medicalclinic.service;

import com.Arek1126.medicalclinic.exception.PatientAlreadyExistsException;
import com.Arek1126.medicalclinic.exception.PatientNotFoundException;
import com.Arek1126.medicalclinic.mapper.PatientMapper;
import com.Arek1126.medicalclinic.model.Patient;
import com.Arek1126.medicalclinic.model.PatientDTO;
import com.Arek1126.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDTO> getAll() {
        return patientMapper.toDTOList(patientRepository.findAll());
    }

   public PatientDTO getByEmail(String email){
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(PatientNotFoundException::new);
        return patientMapper.toDTO(patient);
   }

   public PatientDTO save(Patient newPatient){
        Patient patient = patientRepository.addPatient(newPatient)
                .orElseThrow(PatientAlreadyExistsException::new);
        return patientMapper.toDTO(patient);
   }

   public void deleteByEmail(String email){
        if(!patientRepository.deleteByEmail(email)){
            throw new PatientNotFoundException();
        }
   }

    public PatientDTO modifyByEmail(String email, Patient modifiedPatient){
        Patient patient = patientRepository.modifyByEmail(email, modifiedPatient)
                .orElseThrow(PatientNotFoundException::new);
        return patientMapper.toDTO(patient);
    }

    public void changePassword(String email, String newPassword){
        if(!patientRepository.changePassword(email, newPassword)) {
                throw new PatientNotFoundException();
        }
    }

    private PatientDTO mapToDTO(Patient patient){
        return new PatientDTO(patient.getLastName(), patient.getFirstName(), patient.getEmail());
    }
}
