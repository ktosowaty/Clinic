package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.visit.AvailabilityDto;
import pl.edu.wat.wcy.dto.visit.PatientVisitRequestDto;
import pl.edu.wat.wcy.dto.visit.SecretaryVisitRequestDto;
import pl.edu.wat.wcy.dto.visit.VisitResponseDto;
import pl.edu.wat.wcy.exception.AuthenticationException;
import pl.edu.wat.wcy.model.person.doctor.Specialization;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.security.AuthenticatedUser;
import pl.edu.wat.wcy.service.VisitService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/visit")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/doctor/specialization/{specialization}")
    public ResponseEntity<List<AvailabilityDto>> getAvailabilitiesForSpecialization(@AuthenticationPrincipal AuthenticatedUser user,
                                                                                    @PathVariable Specialization specialization) {
        if (user.getUserType() != UserType.SECRETARY && user.getUserType() != UserType.PATIENT)
            throw new AuthenticationException(user.getUsername());
        List<AvailabilityDto> availabilities = visitService.findVisitDatesForSpecialization(specialization);
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }

    @GetMapping("/doctor/{firstName}-{surname}")
    public ResponseEntity<List<AvailabilityDto>> getAvailabilitiesForDoctor(@AuthenticationPrincipal AuthenticatedUser user,
                                                                            @PathVariable String firstName,
                                                                            @PathVariable String surname) {
        if (user.getUserType() != UserType.SECRETARY && user.getUserType() != UserType.PATIENT)
            throw new AuthenticationException(user.getUsername());
        List<AvailabilityDto> availabilities = visitService.findVisitDatesForDoctor(firstName, surname);
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }

    @PostMapping("/secretary/reservation")
    public ResponseEntity<VisitResponseDto> postVisitBySecretary(@AuthenticationPrincipal AuthenticatedUser user,
                                                                 @RequestBody SecretaryVisitRequestDto request) {
        if (user.getUserType() != UserType.SECRETARY) throw new AuthenticationException(user.getUsername());
        VisitResponseDto response = visitService.reserveVisitBySecretary(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/reservation")
    public ResponseEntity<VisitResponseDto> postVisitByPatient(@AuthenticationPrincipal AuthenticatedUser user,
                                                               @RequestBody PatientVisitRequestDto request) {
        if (user.getUserType() != UserType.PATIENT)
            throw new AuthenticationException(user.getUsername());
        VisitResponseDto response = visitService.reserveVisitByPatient(user, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
