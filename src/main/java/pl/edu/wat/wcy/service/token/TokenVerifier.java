package pl.edu.wat.wcy.service.token;

import pl.edu.wat.wcy.security.AuthenticatedUser;

import java.util.Optional;

public interface TokenVerifier {
    Optional<AuthenticatedUser> verify(String token);
}
