package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.auth.LoginRequestDto;
import pl.edu.wat.wcy.dto.auth.LoginResponseDto;
import pl.edu.wat.wcy.service.auth.PasswordAuthenticator;

@RestController
@CrossOrigin
@RequestMapping(value = "/public/auth")
public class AuthenticationController {
    private final PasswordAuthenticator authenticator;

    @Autowired
    public AuthenticationController(PasswordAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto response = authenticator.login(request.getUsername(), request.getPassword());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
