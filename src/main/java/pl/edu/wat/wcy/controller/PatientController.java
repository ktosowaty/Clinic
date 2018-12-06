package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.PatientDto;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.service.PatientService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/pesel/{peselStr}")
    public ResponseEntity<Patient> getPatientByPesel(@PathVariable String peselStr) {
        Patient patient = patientService.findPatientByPesel(peselStr);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/name/{firstName}-{surname}")
    public ResponseEntity<Patient> getPatientByName(@PathVariable String firstName,
                                                    @PathVariable String surname) {
        Patient patient = patientService.findPatientByName(firstName, surname);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PatientDto> postPatient(@RequestBody PatientDto patientDto) {
        patientService.savePatient(patientDto);
        return new ResponseEntity<>(patientDto, HttpStatus.CREATED);
    }
}
