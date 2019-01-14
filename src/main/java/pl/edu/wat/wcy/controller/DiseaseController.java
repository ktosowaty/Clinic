package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.disease.DiseaseDto;
import pl.edu.wat.wcy.dto.disease.DiseaseProjection;
import pl.edu.wat.wcy.exception.AuthenticationException;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.security.AuthenticatedUser;
import pl.edu.wat.wcy.service.DiseaseService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/disease")
public class DiseaseController {
    private final DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<DiseaseProjection> getDisease(@AuthenticationPrincipal AuthenticatedUser user,
                                                        @PathVariable String name) {
        if (user.getUserType() != UserType.DOCTOR) throw new AuthenticationException(user.getUsername());
        DiseaseProjection projection = diseaseService.findDisease(name);
        return new ResponseEntity<>(projection, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DiseaseDto> postDisease(@AuthenticationPrincipal AuthenticatedUser user,
                                                  @RequestBody DiseaseDto diseaseDto) {
        if (user.getUserType() != UserType.DOCTOR) throw new AuthenticationException(user.getUsername());
        diseaseService.saveDisease(diseaseDto);
        return new ResponseEntity<>(diseaseDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiseaseProjection>> getDiseases(@AuthenticationPrincipal AuthenticatedUser user) {
        if (user.getUserType() != UserType.DOCTOR) throw new AuthenticationException(user.getUsername());
        List<DiseaseProjection> diseases = diseaseService.findDiseases();
        return new ResponseEntity<>(diseases, HttpStatus.OK);
    }
}
