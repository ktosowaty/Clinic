package pl.edu.wat.wcy.repository;

import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.model.person.data.Pesel;
import pl.edu.wat.wcy.model.person.patient.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends PersonRepository<Patient> {
    Optional<Patient> findByPersonalDataPesel(Pesel pesel);

    Optional<Patient> findByPersonalDataNameFirstNameAndPersonalDataNameSurname(String firstName, String surname);
}
