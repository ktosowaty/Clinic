package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.opinion.OpinionRequestDto;
import pl.edu.wat.wcy.dto.opinion.OpinionProjection;
import pl.edu.wat.wcy.dto.opinion.OpinionResponseDto;
import pl.edu.wat.wcy.service.OpinionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/opinion")
public class OpinionController {
    private final OpinionService opinionService;

    @Autowired
    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }

    @GetMapping("/doctor/{firstName}-{surname}")
    public ResponseEntity<List<OpinionProjection>> getOpinionsForDoctor(@PathVariable String firstName,
                                                                        @PathVariable String surname) {
        List<OpinionProjection> opinions = opinionService.findOpinionsForDoctor(firstName, surname);
        return new ResponseEntity<>(opinions, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OpinionResponseDto> postOpinion(@RequestBody OpinionRequestDto request) {
        OpinionResponseDto response = opinionService.saveOpinion(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
