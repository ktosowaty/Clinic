package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.user.UserRequestDto;
import pl.edu.wat.wcy.dto.user.UserResponseDto;
import pl.edu.wat.wcy.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> postUser(@RequestBody UserRequestDto request) {
        UserResponseDto response = userService.saveUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
