package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.dto.doctor.DoctorProjection;
import pl.edu.wat.wcy.exception.AuthenticationException;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.security.AuthenticatedUser;
import pl.edu.wat.wcy.service.DoctorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorProjection>> getDoctors(@AuthenticationPrincipal AuthenticatedUser user) {
        if (user.getUserType() != UserType.PATIENT) throw new AuthenticationException(user.getUsername());
        List<DoctorProjection> doctors = doctorService.findDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
