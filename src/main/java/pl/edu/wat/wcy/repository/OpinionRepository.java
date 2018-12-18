package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.model.opinion.Opinion;
import pl.edu.wat.wcy.dto.opinion.OpinionProjection;
import pl.edu.wat.wcy.model.person.data.Name;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.patient.Patient;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<OpinionProjection> findAllByDoctorFullNameName(Name name);

    Optional<Opinion> findByPatientAndDoctor(Patient patient, Doctor doctor);
}
