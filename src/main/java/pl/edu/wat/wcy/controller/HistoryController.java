package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.history.HistoryRecordDto;
import pl.edu.wat.wcy.dto.history.PatientDiseaseDto;
import pl.edu.wat.wcy.exception.AuthenticationException;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.security.AuthenticatedUser;
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
    public ResponseEntity<List<PatientDiseaseDto>> getPatientHistory(@AuthenticationPrincipal AuthenticatedUser user,
                                                                     @PathVariable String pesel) {
        if (user.getUserType() != UserType.DOCTOR) throw new AuthenticationException(user.getUsername());
        List<PatientDiseaseDto> history = historyService.generatePatientHistory(pesel);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/patient/own")
    public ResponseEntity<List<PatientDiseaseDto>> getOwnHistory(@AuthenticationPrincipal AuthenticatedUser user) {
        if (user.getUserType() != UserType.PATIENT) throw new AuthenticationException(user.getUsername());
        List<PatientDiseaseDto> history = historyService.generateOwnHistory(user);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @PostMapping("/create-record")
    public ResponseEntity<HistoryRecordDto> postHistoryRecord(@AuthenticationPrincipal AuthenticatedUser user,
                                                              @RequestBody HistoryRecordDto historyRecordDto) {
        if (user.getUserType() != UserType.DOCTOR) throw new AuthenticationException(user.getUsername());
        historyService.saveRecord(historyRecordDto);
        return new ResponseEntity<>(historyRecordDto, HttpStatus.CREATED);
    }
}
