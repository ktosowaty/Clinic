package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.person.PatientDto;
import pl.edu.wat.wcy.dto.person.PatientProjection;
import pl.edu.wat.wcy.exception.AuthenticationException;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.security.AuthenticatedUser;
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
    public ResponseEntity<PatientProjection> getPatientByPesel(@AuthenticationPrincipal AuthenticatedUser user,
                                                               @PathVariable String peselStr) {
        if (user.getUserType() != UserType.SECRETARY && user.getUserType() != UserType.DOCTOR)
            throw new AuthenticationException(user.getUsername());
        PatientProjection patient = patientService.findPatientByPesel(peselStr);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/name/{firstName}-{surname}")
    public ResponseEntity<PatientProjection> getPatientByName(@AuthenticationPrincipal AuthenticatedUser user,
                                                              @PathVariable String firstName,
                                                              @PathVariable String surname) {
        if (user.getUserType() != UserType.SECRETARY && user.getUserType() != UserType.DOCTOR)
            throw new AuthenticationException(user.getUsername());
        PatientProjection patient = patientService.findPatientByName(firstName, surname);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/register/secretary")
    public ResponseEntity<PatientDto> postPatientBySecretary(@AuthenticationPrincipal AuthenticatedUser user,
                                                             @RequestBody PatientDto patientDto) {
        if (user.getUserType() != UserType.SECRETARY) throw new AuthenticationException(user.getUsername());
        patientService.savePatientBySecretary(patientDto);
        return new ResponseEntity<>(patientDto, HttpStatus.CREATED);
    }

    @PostMapping("/register/own")
    public ResponseEntity<PatientDto> postPatientByHimself(@AuthenticationPrincipal AuthenticatedUser user,
                                                           @RequestBody PatientDto patientDto) {
        if (user.getUserType() != UserType.PATIENT) throw new AuthenticationException(user.getUsername());
        patientService.savePatientByHimself(user, patientDto);
        return new ResponseEntity<>(patientDto, HttpStatus.CREATED);
    }
}
