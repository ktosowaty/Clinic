package pl.edu.wat.wcy.security;

public interface PasswordAuthenticator {
    String login(String username, String password) throws AuthenticationException;

    void logout(String token) throws AuthenticationException;
}
