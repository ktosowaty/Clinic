package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.model.visit.Visit;
import pl.edu.wat.wcy.model.person.doctor.Doctor;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    Optional<Visit> findByVisitStartAndDoctor(LocalDateTime visitStart, Doctor doctor);
}
