package pl.edu.wat.wcy.repository;

import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.model.person.doctor.Doctor;

@Repository
public interface DoctorRepository extends PersonRepository<Doctor> {
}
