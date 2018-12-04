package pl.edu.wat.wcy.model.person.data;

import javax.persistence.Column;

import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

public class Name {
    @Column(nullable = false, updatable = false, length = 20)
    private String firstName;

    @Column(updatable = false, length = 20)
    private String secondName;

    @Column(nullable = false, updatable = false, length = 40)
    private String surname;

    private Name() {
        // JPA
    }

    public Name(String firstName, String secondName, String surname) {
        this.firstName = requireNonNull(firstName, "first name");
        this.secondName = secondName;
        this.surname = requireNonNull(surname, "surname");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName);
    }
}
