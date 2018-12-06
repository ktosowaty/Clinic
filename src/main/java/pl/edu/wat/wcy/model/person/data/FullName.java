package pl.edu.wat.wcy.model.person.data;

import javax.persistence.Column;
import javax.persistence.Embedded;

import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

public class FullName {
    @Embedded
    private Name name;

    @Column(updatable = false, length = 20)
    private String secondName;

    private FullName() {
        // JPA
    }

    public FullName(Name name, String secondName) {
        this.name = requireNonNull(name, "name");
        this.secondName = secondName;
    }

    public Name getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(name, fullName.name) &&
                Objects.equals(secondName, fullName.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName);
    }
}
