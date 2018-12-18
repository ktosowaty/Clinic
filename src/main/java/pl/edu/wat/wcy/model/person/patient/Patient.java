package pl.edu.wat.wcy.model.person.patient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.edu.wat.wcy.model.person.Person;
import pl.edu.wat.wcy.model.person.data.*;
import pl.edu.wat.wcy.model.person.data.address.Address;
import pl.edu.wat.wcy.model.person.data.name.FullName;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient extends Person {
    protected Patient() {}

    public Patient(FullName fullName, Pesel pesel, Gender gender, LocalDate birthDate,
                   Address address, PhoneNumber phoneNumber) {
        super(fullName, pesel, gender, birthDate, address, phoneNumber);
    }
}
