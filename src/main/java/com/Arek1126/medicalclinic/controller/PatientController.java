package com.Arek1126.medicalclinic.controller;

import com.Arek1126.medicalclinic.model.Patient;
import com.Arek1126.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @GetMapping
    public List<Patient> getAll() {
        return service.getAll();
    }

    @GetMapping("/{email}")
    public Patient getByEmail(@PathVariable String email) {
        return service.getByEmail(email);
    }

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addPatient(patient));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable String email){
        service.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{email}")
    public Patient modifyByEmail(@PathVariable String email, @RequestBody Patient patient) {
        return service.modifyPatient(email, patient);
    }

    @PatchMapping("/{email}/password")
    public ResponseEntity<Void> changePassword(@PathVariable String email, @RequestBody String newPassword){
        service.changePassword(email, newPassword);
        return ResponseEntity.noContent().build();
    }


}
