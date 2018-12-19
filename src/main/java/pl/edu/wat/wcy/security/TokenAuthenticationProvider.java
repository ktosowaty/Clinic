package pl.edu.wat.wcy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.edu.wat.wcy.service.token.TokenVerifier;

import java.util.Optional;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Component
final class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final TokenVerifier tokenVerifier;

    @Autowired
    public TokenAuthenticationProvider(TokenVerifier tokenVerifier) {
        this.tokenVerifier = requireNonNull(tokenVerifier, "token verifier");
    }

    @Override
    protected void additionalAuthenticationChecks(final UserDetails user,
                                                  final UsernamePasswordAuthenticationToken auth) {
        // No additional authentication checks.
    }

    @Override
    protected UserDetails retrieveUser(final String username,
                                       final UsernamePasswordAuthenticationToken auth) {
        final Object token = auth.getCredentials();
        return Optional
                .ofNullable(token)
                .map(String::valueOf)
                .flatMap(tokenVerifier::verify)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find a user with the token: " + token));
    }

}
