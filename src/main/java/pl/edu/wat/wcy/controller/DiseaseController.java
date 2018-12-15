package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.DiseaseDto;
import pl.edu.wat.wcy.dto.DiseaseProjection;
import pl.edu.wat.wcy.service.DiseaseService;

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
    public ResponseEntity<DiseaseProjection> getDisease(@PathVariable String name) {
        DiseaseProjection diseaseProjection = diseaseService.findDisease(name);
        return new ResponseEntity<>(diseaseProjection, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DiseaseDto> postDisease(@RequestBody DiseaseDto diseaseDto) {
        diseaseService.saveDisease(diseaseDto);
        return new ResponseEntity<>(diseaseDto, HttpStatus.CREATED);
    }
}
