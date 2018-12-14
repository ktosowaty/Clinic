package pl.edu.wat.wcy.model.person.data;

import javax.persistence.Column;
import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

public class Name {
    @Column(nullable = false, updatable = false, length = 20)
    private String firstName;

    @Column(nullable = false, updatable = false, length = 40)
    private String surname;

    private Name() {}

    public Name(String firstName, String surname) {
        this.firstName = requireNonNull(firstName, "first name");
        this.surname = requireNonNull(surname, "surname");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) &&
                Objects.equals(surname, name.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname);
    }

    @Override
    public String toString() {
        return firstName + " " + surname;
    }
}
