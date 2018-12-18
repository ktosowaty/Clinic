package pl.edu.wat.wcy.model.person.account;

import javax.persistence.Column;
import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

public class Username {
    private static final int MIN_LOGIN_LENGTH = 5;
    private static final int MAX_LOGIN_LENGTH = 20;

    @Column(length = MAX_LOGIN_LENGTH, nullable = false)
    private String username;

    private Username() {}

    public Username(String username) {
        checkUsername(username);
        this.username = username
                .trim()
                .toLowerCase();
    }

    private void checkUsername(String username) {
        requireNonNull(username, "username");
        if (!username.matches("[a-zA-Z0-9]+"))
            throw new IllegalArgumentException("Username " + username + " is not alphanumeric.");
        if (username.length() < MIN_LOGIN_LENGTH)
            throw new IllegalArgumentException("Username " + username + " is too short.");
        if (username.length() > MAX_LOGIN_LENGTH)
            throw new IllegalArgumentException("Username " + username + " is too long.");
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return Objects.equals(username, username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return username;
    }
}
