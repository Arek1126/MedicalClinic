package com.Arek1126.medicalclinic.mapper;

import com.Arek1126.medicalclinic.model.Patient;
import com.Arek1126.medicalclinic.model.PatientDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface PatientMapper {

    PatientDTO toDTO(Patient patient);
    List<PatientDTO> toDTOList(List<Patient>patients);
}
