package pl.edu.wat.wcy.model.person.patient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.edu.wat.wcy.model.person.Person;
import pl.edu.wat.wcy.model.person.data.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient extends Person {
    protected Patient() {
        // JPA
    }

    public Patient(FullName fullName, Gender gender, LocalDate birthDate, Pesel pesel,
                   Address address, PhoneNumber phoneNumber) {
        super(fullName, gender, birthDate, pesel, address, phoneNumber);
    }
}
