package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.AvailabilityDto;
import pl.edu.wat.wcy.dto.VisitDto;
import pl.edu.wat.wcy.model.person.doctor.Specialization;
import pl.edu.wat.wcy.service.VisitService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/availability")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/doctor/specialization/{specialization}")
    public ResponseEntity<List<AvailabilityDto>> getAvailabilitiesForSpecialization(@PathVariable Specialization specialization) {
        List<AvailabilityDto> availabilities = visitService.findVisitDatesForSpecialization(specialization);
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }

    @GetMapping("/doctor/{firstName}-{surname}")
    public ResponseEntity<List<AvailabilityDto>> getAvailabilitiesForDoctor(@PathVariable String firstName,
                                                                            @PathVariable String surname) {
        List<AvailabilityDto> availabilities = visitService.findVisitDatesForDoctor(firstName, surname);
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }

    @PostMapping("/reservation")
    public ResponseEntity<VisitDto> postVisit(@RequestBody VisitDto visitDto) {
        visitService.reserveVisit(visitDto);
        return new ResponseEntity<>(visitDto, HttpStatus.CREATED);
    }
}
