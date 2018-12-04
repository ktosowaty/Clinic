package pl.edu.wat.wcy.model.person;

import pl.edu.wat.wcy.model.person.account.Account;
import pl.edu.wat.wcy.model.person.data.PersonalData;

import javax.persistence.*;
import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
    @Id
    @GeneratedValue
    private long id;

    @Embedded
    private PersonalData personalData;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    protected Person() {
        // JPA
    }

    public Person(PersonalData personalData) {
        this.personalData = requireNonNull(personalData, "personal data");
    }

    public long getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
