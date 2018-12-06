package pl.edu.wat.wcy.repository;

import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.model.person.data.Name;
import pl.edu.wat.wcy.model.person.data.Pesel;
import pl.edu.wat.wcy.model.person.patient.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends PersonRepository<Patient> {
    Optional<Patient> findByPesel(Pesel pesel);

    Optional<Patient> findByFullNameName(Name name);
}
