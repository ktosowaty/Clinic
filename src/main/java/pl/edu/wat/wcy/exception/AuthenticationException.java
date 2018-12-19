package pl.edu.wat.wcy.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String username) {
        super("Can't authenticate the user: " + username + ".");
    }
}
