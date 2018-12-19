package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.opinion.OpinionRequestDto;
import pl.edu.wat.wcy.dto.opinion.OpinionProjection;
import pl.edu.wat.wcy.dto.opinion.OpinionResponseDto;
import pl.edu.wat.wcy.exception.AuthenticationException;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.security.AuthenticatedUser;
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

    @Secured("PATIENT")
    @GetMapping("/doctor/{firstName}-{surname}")
    public ResponseEntity<List<OpinionProjection>> getOpinionsForDoctor(@AuthenticationPrincipal AuthenticatedUser user,
                                                                        @PathVariable String firstName,
                                                                        @PathVariable String surname) {
        if (user.getUserType() != UserType.PATIENT) throw new AuthenticationException(user.getUsername());
        List<OpinionProjection> opinions = opinionService.findOpinionsForDoctor(firstName, surname);
        return new ResponseEntity<>(opinions, HttpStatus.OK);
    }

    @Secured("PATIENT")
    @PostMapping("/create")
    public ResponseEntity<OpinionResponseDto> postOpinion(@AuthenticationPrincipal AuthenticatedUser user,
                                                          @RequestBody OpinionRequestDto request) {
        if (user.getUserType() != UserType.PATIENT) throw new AuthenticationException(user.getUsername());
        OpinionResponseDto response = opinionService.saveOpinion(user, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
