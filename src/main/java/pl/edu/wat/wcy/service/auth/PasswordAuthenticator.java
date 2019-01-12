package pl.edu.wat.wcy.service.auth;

import pl.edu.wat.wcy.dto.auth.LoginResponseDto;
import pl.edu.wat.wcy.exception.AuthenticationException;

public interface PasswordAuthenticator {
    LoginResponseDto login(String username, String password) throws AuthenticationException;

    void logout(String token) throws AuthenticationException;
}
