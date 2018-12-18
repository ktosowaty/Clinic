package pl.edu.wat.wcy.model.person;

import pl.edu.wat.wcy.model.person.account.User;
import pl.edu.wat.wcy.model.person.data.*;
import pl.edu.wat.wcy.model.person.data.address.Address;
import pl.edu.wat.wcy.model.person.data.name.FullName;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

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

    @Column(nullable = false, updatable = false)
    private LocalDate birthDate;

    @Embedded
    private Pesel pesel;

    @Embedded
    private Address address;

    @Embedded
    private PhoneNumber phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    private User user;

    protected Person() {}

    public Person(FullName fullName, Pesel pesel, Gender gender, LocalDate birthDate,
                  Address address, PhoneNumber phoneNumber) {
        this.fullName = requireNonNull(fullName, "full name");
        this.pesel = requireNonNull(pesel, "pesel");
        this.gender = checkGender(gender, pesel);
        this.birthDate = checkBirthDate(birthDate, pesel);
        this.address = requireNonNull(address, "address");
        this.phoneNumber = requireNonNull(phoneNumber, "phone number");
    }

    private Gender checkGender(Gender gender, Pesel pesel) {
        requireNonNull(gender, "gender");
        Gender peselGender = pesel.getGender();
        if (gender != peselGender)
            throw new IllegalArgumentException("Given gender doesn't match the pesel one.");
        return gender;
    }

    private LocalDate checkBirthDate(LocalDate birthDate, Pesel pesel) {
        requireNonNull(birthDate, "birth date");
        LocalDate peselBirthDate = pesel.getBirthDate();
        if (!birthDate.equals(peselBirthDate))
            throw new IllegalArgumentException("Given birth date doesn't match the pesel one.");
        return birthDate;
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

    public void setUser(User user) {
        requireNonNull(user, "user");
        if (this.user != null) throw new IllegalArgumentException("This person already has an account.");
        this.user = user;
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
