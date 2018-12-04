package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.model.person.account.Account;
import pl.edu.wat.wcy.model.person.data.Pesel;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.service.PatientService;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{pesel}")
    public ResponseEntity<Patient> getPatientByPesel(@PathVariable Pesel pesel) {
        Optional<Patient> patient = patientService.findPatientByPesel(pesel);
        /*if (patient.isPresent())*/ return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        // TODO: 03.12.2018: http exceptions
        //throw new JakisKurwaException();
    }

    @GetMapping("/{firstName}+{surname}")
    public ResponseEntity<Patient> getPatientByName(@PathVariable String firstName,
                                                     @PathVariable String surname) {
        Optional<Patient> patient = patientService.findPatientByName(firstName, surname);
        /*if (patient.isPresent())*/ return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        // TODO: 03.12.2018: http exceptions
        //throw new JakisKurwaException();
    }

    @PostMapping("/patient/create")
    public ResponseEntity<Patient> postPatient(@RequestBody Patient patient) {
        // TODO: 03.12.2018 to dla sekretarki - zapisanie pacjenta bez konta
        patientService.savePatient(patient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PostMapping("/account/create")
    public ResponseEntity<Account> postAccount(@RequestBody Account account) {
        patientService.saveAccount(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
}
