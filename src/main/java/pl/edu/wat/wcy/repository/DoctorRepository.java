package pl.edu.wat.wcy.repository;

import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.dto.doctor.DoctorProjection;
import pl.edu.wat.wcy.model.person.doctor.Doctor;

import java.util.List;

@Repository
public interface DoctorRepository extends PersonRepository<Doctor> {
    List<DoctorProjection> findAllProjectedBy();
}
