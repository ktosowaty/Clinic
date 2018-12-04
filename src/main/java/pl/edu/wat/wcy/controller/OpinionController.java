package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.model.opinion.Opinion;
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

    @GetMapping("/doctor/{surname}")
    public ResponseEntity<List<Opinion>> getOpinionsForDoctor(@PathVariable String surname) {
        List<Opinion> opinions = opinionService.findOpinionsForDoctor(surname);
        return new ResponseEntity<>(opinions, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Opinion> postOpinion(@RequestBody Opinion opinion) {
        opinionService.saveOpinion(opinion);
        return new ResponseEntity<>(opinion, HttpStatus.CREATED);
    }
}
