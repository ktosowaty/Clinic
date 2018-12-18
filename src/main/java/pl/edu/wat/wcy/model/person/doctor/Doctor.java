package pl.edu.wat.wcy.model.person.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.person.Person;
import pl.edu.wat.wcy.model.person.data.*;
import pl.edu.wat.wcy.model.person.data.address.Address;

import javax.persistence.*;

import java.time.LocalDate;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Entity
@Table(name = "doctors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor extends Person {
    @Column
    @Enumerated(value = EnumType.STRING)
    private Specialization specialization;

    @Embedded
    private Money visitCost;

    protected Doctor() {
        // JPA
    }

    public Doctor(FullName fullName, Pesel pesel, Gender gender, LocalDate birthDate,
                  Address address, PhoneNumber phoneNumber, Specialization specialization, Money visitCost) {
        super(fullName, pesel, gender, birthDate, address, phoneNumber);
        this.specialization = requireNonNull(specialization, "specialization");
        this.visitCost = requireNonNull(visitCost, "visit cost");
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public Money getVisitCost() {
        return visitCost;
    }
}
