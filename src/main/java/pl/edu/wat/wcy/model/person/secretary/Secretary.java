package pl.edu.wat.wcy.model.person.secretary;

import pl.edu.wat.wcy.model.person.Person;
import pl.edu.wat.wcy.model.person.data.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "secretaries")
public class Secretary extends Person {
    private Secretary() {
        // JPA
    }

    public Secretary(FullName fullName, Gender gender, LocalDate birthDate, Pesel pesel,
                     Address address, PhoneNumber phoneNumber) {
        super(fullName, gender, birthDate, pesel, address, phoneNumber);
    }
}
