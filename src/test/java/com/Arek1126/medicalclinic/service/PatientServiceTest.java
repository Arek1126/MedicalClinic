package com.Arek1126.medicalclinic.service;

import com.Arek1126.medicalclinic.mapper.PatientMapper;
import com.Arek1126.medicalclinic.model.Patient;
import com.Arek1126.medicalclinic.model.PatientDTO;
import com.Arek1126.medicalclinic.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class PatientServiceTest {

    PatientService patientService;
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    @BeforeEach
    void setup()
    {
        this.patientRepository = Mockito.mock(PatientRepository.class);
        this.patientMapper = Mappers.getMapper(PatientMapper.class);
        this.patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void getPatients_DataCorrect_PatientsReturned(){
        //given
        List<Patient> patients = List.of(new Patient("marek.kowalski@example.com", "342mfp", "GFH987", "Marek", "Kowalski", "239282435", LocalDate.of(1983,02, 01)), new Patient("antonin@example.com","mfd912","ZXC314", "Antoni", "Nowak", "192642721", LocalDate.of(1987, 02, 04)));
        when(patientRepository.findAll()).thenReturn(patients);
        //when
        List<PatientDTO> result = patientService.getAll();
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, result.size()),
                () -> Assertions.assertEquals("marek.kowalski@example.com", result.get(0).getEmail()),
                () -> Assertions.assertEquals("Marek", result.get(0).getFirstName()),
                () -> Assertions.assertEquals("Kowalski", result.get(0).getLastName()),
                () -> Assertions.assertEquals("antonin@example.com", result.get(1).getEmail()),
                () -> Assertions.assertEquals("Antoni", result.get(1).getFirstName()),
                () -> Assertions.assertEquals("Nowak", result.get(1).getLastName())
        );
    }

    @Test
    void getByEmail_DataCorrect_PatientReturned(){
        //given
        Patient patient = new Patient("marek.kowalski@example.com", "342mfp", "GFH987", "Marek", "Kowalski", "239282435", LocalDate.of(1983,02, 01));
        when(patientRepository.findByEmail("marek.kowalski@example.com")).thenReturn(Optional.of(patient));
        //when
        PatientDTO result = patientService.getByEmail("marek.kowalski@example.com");
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals("marek.kowalski@example.com", result.getEmail()),
                () -> Assertions.assertEquals("Marek", result.getFirstName()),
                () -> Assertions.assertEquals("Kowalski", result.getLastName())
        );
    }

    @Test
    void addPatient_DataCorrect_PatientReturned(){
        //given
        Patient patient = new Patient("marek.kowalski@example.com", "342mfp", "GFH987", "Marek", "Kowalski", "239282435", LocalDate.of(1983,02, 01));
        when(patientRepository.addPatient(patient)).thenReturn(Optional.of(patient));
        //when
        PatientDTO result = patientService.addPatient(patient);
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals("marek.kowalski@example.com", result.getEmail()),
                () -> Assertions.assertEquals("Marek", result.getFirstName()),
                () -> Assertions.assertEquals("Kowalski", result.getLastName())
        );
    }

    @Test
    void modifyByEmail_DataCorrect_PatientReturned(){
        //given
        Patient patient = new Patient("marek.kowalski@example.com", "342mfp", "GFH987", "Marek", "Kowalski", "239282435", LocalDate.of(1983,02, 01));
        Patient patientsNewData =  new Patient("marek.kowalski@example.com","mfd912","ZXC314", "Antoni", "Nowak", "192642721", LocalDate.of(1987, 02, 04)));
        when(patientRepository.modifyByEmail(patient.getEmail(), patientsNewData)).thenReturn(Optional.of(patientsNewData));
        //when
        PatientDTO result = patientService.modifyByEmail(patient.getEmail(), patientsNewData);
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals("marek.kowalski@example.com", result.getEmail()),
                () -> Assertions.assertEquals("Antoni", result.getFirstName()),
                () -> Assertions.assertEquals("Nowak", result.getLastName())
        );

    }

}
