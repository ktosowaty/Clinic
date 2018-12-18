package pl.edu.wat.wcy.dto.person;

import org.springframework.beans.factory.annotation.Value;
import pl.edu.wat.wcy.model.person.data.name.Name;
import pl.edu.wat.wcy.model.person.data.Pesel;

import java.time.LocalDate;

public interface PatientProjection {
    @Value("#{target.fullName.name}")
    Name getName();

    LocalDate getBirthDate();

    Pesel getPesel();
}
