package pl.edu.wat.wcy.security;

import java.util.Optional;

public interface TokenVerifier {
    Optional<AuthenticatedUser> verify(String token);
}
