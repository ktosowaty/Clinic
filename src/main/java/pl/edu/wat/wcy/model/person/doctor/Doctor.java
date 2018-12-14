package pl.edu.wat.wcy.model.person.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.edu.wat.wcy.model.person.Person;
import pl.edu.wat.wcy.model.person.data.*;
import pl.edu.wat.wcy.model.person.data.address.Address;

import javax.persistence.*;

import java.time.LocalDate;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Table(name = "doctors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor extends Person {
    @Column
    @Enumerated(value = EnumType.STRING)
    private Specialization specialization;

    protected Doctor() {
        // JPA
    }

    public Doctor(FullName fullName, Pesel pesel, Gender gender, LocalDate birthDate,
                  Address address, PhoneNumber phoneNumber, Specialization specialization) {
        super(fullName, pesel, gender, birthDate, address, phoneNumber);
        this.specialization = requireNonNull(specialization, "specialization");
    }

    public Specialization getSpecialization() {
        return specialization;
    }
}
