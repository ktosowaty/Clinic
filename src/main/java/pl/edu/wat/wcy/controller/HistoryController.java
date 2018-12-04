package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.PatientDiseaseDto;
import pl.edu.wat.wcy.service.HistoryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/patient/{pesel}")
    public ResponseEntity<List<PatientDiseaseDto>> getPatientHistory(@PathVariable String pesel) {
        List<PatientDiseaseDto> opinions = historyService.generatePatientHistory(pesel);
        return new ResponseEntity<>(opinions, HttpStatus.OK);
    }
}
