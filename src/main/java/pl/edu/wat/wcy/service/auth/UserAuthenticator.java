package pl.edu.wat.wcy.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.auth.LoginResponseDto;
import pl.edu.wat.wcy.exception.AuthenticationException;
import pl.edu.wat.wcy.model.person.user.Email;
import pl.edu.wat.wcy.model.person.user.User;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.model.person.user.Username;
import pl.edu.wat.wcy.repository.UserRepository;
import pl.edu.wat.wcy.service.token.TokenGenerator;

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
    public LoginResponseDto login(String username, String password) throws AuthenticationException {
        User user = findUser(username);
        if (!user.getPassword().matches(password)) throw new AuthenticationException(username);
        Username name = user.getUsername();
        Email email = user.getEmail();
        UserType userType = user.getUserType();
        long personId = user.getPerson().getId();
        String token = newToken(user);
        return new LoginResponseDto(name, email, userType, personId, token);
    }

    private User findUser(String username) {
        return userRepository.findByUsername(new Username(username))
                .orElseThrow(() -> new AuthenticationException(username));
    }

    private String newToken(User user) {
        long id = user.getId();
        String username = user.getUsername().getUsername();
        String userType = user.getUserType().name();
        return tokenGenerator.expiring(id, username, userType);
    }

    @Override
    public void logout(String token) throws AuthenticationException {
        // todo: revoke 'self-containing' tokens
    }
}
