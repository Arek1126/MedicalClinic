package com.Arek1126.medicalclinic.controller;


import com.Arek1126.medicalclinic.model.Patient;
import com.Arek1126.medicalclinic.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service){
        this.service = service;
    }

    @GetMapping
    public List<Patient> getPatients()
    {
        return service.getPatients();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable String email) {
        return service.getPatientByEmail(email).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        boolean added = service.addPatient(patient);
        if(!added)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletePatientByEmail(@PathVariable String email){
        boolean deleted = service.deletePatientByEmail(email);
        if(!deleted)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<Void> modifyPatient(@PathVariable String email, @RequestBody Patient patient){
        boolean modified = service.modifyPatient(email, patient);
        if(!modified)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
