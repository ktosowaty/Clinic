package pl.edu.wat.wcy.model.person.secretary;

import pl.edu.wat.wcy.model.person.Person;
import pl.edu.wat.wcy.model.person.data.*;
import pl.edu.wat.wcy.model.person.data.address.Address;
import pl.edu.wat.wcy.model.person.data.name.FullName;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "secretaries")
public class Secretary extends Person {
    private Secretary() {}

    public Secretary(FullName fullName, Pesel pesel, Gender gender, LocalDate birthDate,
                     Address address, PhoneNumber phoneNumber) {
        super(fullName, pesel, gender, birthDate, address, phoneNumber);
    }
}
