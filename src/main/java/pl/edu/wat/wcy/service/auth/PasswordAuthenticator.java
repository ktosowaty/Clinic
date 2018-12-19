package pl.edu.wat.wcy.service.auth;

import pl.edu.wat.wcy.exception.AuthenticationException;

public interface PasswordAuthenticator {
    String login(String username, String password) throws AuthenticationException;

    void logout(String token) throws AuthenticationException;
}
