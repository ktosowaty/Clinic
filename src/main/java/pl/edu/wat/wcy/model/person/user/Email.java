package pl.edu.wat.wcy.model.person.user;

import javax.persistence.Column;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

public class Email {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Column
    private String email;

    private Email() {}

    public Email(String email) {
        requireNonNull(email, "email");
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (matcher.find()) this.email = email;
        else throw new IllegalArgumentException("Wrong email address: " + email + ".");
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
