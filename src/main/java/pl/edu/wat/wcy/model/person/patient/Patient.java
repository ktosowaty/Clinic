package pl.edu.wat.wcy.model.person.patient;

import pl.edu.wat.wcy.model.person.Person;
import pl.edu.wat.wcy.model.person.data.PersonalData;

import javax.persistence.*;

@Entity
@Table(name = "patients")
public class Patient extends Person {
    private Patient() {
        // JPA
    }

    public Patient(PersonalData personalData) {
        super(personalData);
    }
}
