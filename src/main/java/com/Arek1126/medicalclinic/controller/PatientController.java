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
    public ResponseEntity<Patient> getByEmail(@PathVariable String email) {
        return service.getByEmail(email).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        return service.addPatient(patient)
                .map(addedPatient -> ResponseEntity.status(HttpStatus.CREATED).body(addedPatient))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable String email){
        if(!service.deleteByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<Patient> modifyByEmail(@PathVariable String email, @RequestBody Patient patient) {
        return service.modifyPatient(email, patient)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{email}/password")
    public ResponseEntity<Void> changePassword(@PathVariable String email, @RequestBody String newPassword){
        service.changePassword(email, newPassword);
        return ResponseEntity.noContent().build();
    }


}
