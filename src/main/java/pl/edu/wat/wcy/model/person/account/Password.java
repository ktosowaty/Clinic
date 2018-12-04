package pl.edu.wat.wcy.model.person.account;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Column;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

public class Password {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 30;
    private static final int LOG_ROUNDS = 12;

    @Column(nullable = false)
    private String hashedPassword;

    private Password() {
        // JPA
    }

    public Password(String plainPassword) {
        checkPassword(plainPassword);
        hashedPassword = hashPassword(plainPassword);
    }

    private void checkPassword(String plainPassword) {
        requireNonNull(plainPassword, "plain password");
        if (plainPassword.length() < MIN_PASSWORD_LENGTH)
            throw new IllegalArgumentException("Password " + plainPassword + " is too short.");
        if (plainPassword.length() > MAX_PASSWORD_LENGTH)
            throw new IllegalArgumentException("Password " + plainPassword + " is too long.");
    }

    private String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt(LOG_ROUNDS);
        return BCrypt.hashpw(plainPassword, salt);
    }

    public boolean matches(String plainPassword) {
        requireNonNull(plainPassword, "Plain password");
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}


