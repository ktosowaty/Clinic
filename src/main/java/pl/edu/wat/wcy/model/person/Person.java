package pl.edu.wat.wcy.model.person;

import pl.edu.wat.wcy.model.person.account.Account;
import pl.edu.wat.wcy.model.person.data.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Embedded
    private FullName fullName;

    @Column(nullable = false, updatable = false, length = 12)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column
    private LocalDate birthDate;

    @Embedded
    private Pesel pesel;

    @Embedded
    private Address address;

    @Embedded
    private PhoneNumber phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    protected Person() {
        // JPA
    }

    // todo: pesel+birthDate check
    // todo: pesel+gender check
    public Person(FullName fullName, Gender gender, LocalDate birthDate, Pesel pesel,
                  Address address, PhoneNumber phoneNumber) {
        this.fullName = requireNonNull(fullName, "full name");
        this.gender = requireNonNull(gender, "gender");
        this.birthDate = requireNonNull(birthDate, "birth date");
        this.pesel = requireNonNull(pesel, "pesel");
        this.address = requireNonNull(address, "address");
        this.phoneNumber = requireNonNull(phoneNumber, "phone number");
    }

    public long getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Pesel getPesel() {
        return pesel;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
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
