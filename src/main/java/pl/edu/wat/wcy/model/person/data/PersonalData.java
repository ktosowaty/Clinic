package pl.edu.wat.wcy.model.person.data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

public class PersonalData {

    // todo: pesel+birthDate check
    // todo: pesel+gender check

    @Embedded
    private Name name;

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

    private PersonalData() {
        // JPA
    }

    public PersonalData(Name name, Gender gender, LocalDate birthDate, Pesel pesel,
                        Address address, PhoneNumber phoneNumber) {
        this.name = requireNonNull(name, "name");
        this.gender = requireNonNull(gender, "gender");
        this.birthDate = requireNonNull(birthDate, "birth date");
        this.pesel = requireNonNull(pesel, "pesel");
        this.address = requireNonNull(address, "address");
        this.phoneNumber = requireNonNull(phoneNumber, "phone number");
    }

    public Name getName() {
        return name;
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
        PersonalData that = (PersonalData) o;
        return Objects.equals(pesel, that.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }
}
