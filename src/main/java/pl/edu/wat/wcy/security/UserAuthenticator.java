package pl.edu.wat.wcy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.model.person.account.User;
import pl.edu.wat.wcy.model.person.account.Username;
import pl.edu.wat.wcy.repository.UserRepository;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Service
public class UserAuthenticator implements PasswordAuthenticator {
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    @Autowired
    public UserAuthenticator(UserRepository userRepository, TokenGenerator tokenGenerator) {
        this.userRepository = requireNonNull(userRepository);
        this.tokenGenerator = requireNonNull(tokenGenerator);
    }

    @Override
    public String login(String username, String password) throws AuthenticationException {
        User user = findUser(username);
        if (user.getPassword().matches(password)) return newToken(user);

        throw new AuthenticationException(username);
    }

    private User findUser(String username) {
        return userRepository
                .findByUsername(new Username(username))
                .orElseThrow(() -> new AuthenticationException(username));
    }

    private String newToken(User user) {
        long id = user.getId();
        String username = user.getUsername().getUsername();
        return tokenGenerator.expiring(id, username);
    }

    @Override
    public void logout(String token) throws AuthenticationException {
        // todo: revoke 'self-containing' tokens
    }

}
