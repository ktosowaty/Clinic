package pl.edu.wat.wcy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.dto.AccountDto;
import pl.edu.wat.wcy.service.AccountService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> postAccount(@RequestBody AccountDto accountDto) {
        accountService.saveAccount(accountDto);
        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }
}
