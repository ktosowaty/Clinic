package pl.edu.wat.wcy.model.person.doctor;

import pl.edu.wat.wcy.model.person.Person;
import pl.edu.wat.wcy.model.person.data.PersonalData;

import javax.persistence.*;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {
    @Column
    @Enumerated(value = EnumType.STRING)
    private Specialization specialization;

    private Doctor() {
        // JPA
    }

    public Doctor(PersonalData personalData, Specialization specialization) {
        super(personalData);
        this.specialization = requireNonNull(specialization, "specialization");
    }

    public Specialization getSpecialization() {
        return specialization;
    }
}
