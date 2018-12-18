package pl.edu.wat.wcy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    TokenDto login(@RequestBody LoginDto request) {
        String token = authenticator.login(request.getUsername(), request.getPassword());
        return new TokenDto(token);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<Object> handleAuthenticationException() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
