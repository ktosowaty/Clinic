package pl.edu.wat.wcy.model.person.account;

import javax.persistence.Column;
import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

public class Login {
    private static final int MIN_LOGIN_LENGTH = 5;
    private static final int MAX_LOGIN_LENGTH = 20;

    @Column(length = MAX_LOGIN_LENGTH, nullable = false)
    private String login;

    private Login() {
        // JPA
    }

    public Login(String login) {
        checkLogin(login);
        this.login = login
                .trim()
                .toLowerCase();
    }

    private void checkLogin(String login) {
        requireNonNull(login, "login");
        if (!login.matches("[a-zA-Z0-9]+")) throw new IllegalArgumentException("Login " + login + " is not alphanumeric.");
        if (login.length() < MIN_LOGIN_LENGTH) throw new IllegalArgumentException("Login " + login + " is too short.");
        if (login.length() > MAX_LOGIN_LENGTH) throw new IllegalArgumentException("Login " + login + " is too long.");
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return Objects.equals(login, login1.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
