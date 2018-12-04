package pl.edu.wat.wcy.model.person.secretary;

import pl.edu.wat.wcy.model.person.Person;
import pl.edu.wat.wcy.model.person.data.PersonalData;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "secretaries")
public class Secretary extends Person {
    private Secretary() {
        // JPA
    }

    public Secretary(PersonalData personalData) {
        super(personalData);
    }
}
