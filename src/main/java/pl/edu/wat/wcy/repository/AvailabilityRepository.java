package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.dto.visit.AvailabilityDto;
import pl.edu.wat.wcy.model.person.doctor.Availability;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.doctor.Specialization;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    @Query("select new pl.edu.wat.wcy.dto.visit.AvailabilityDto (d.id, d.fullName.name.firstName, " +
            "d.fullName.name.surname, d.specialization, a.visitStart) " +
            "from Doctor as d " +
            "left join Availability as a on a.doctor.id = d.id " +
            "left join Visit as v on a.visitStart = v.visitStart " +
            "where v.visitStart is null " +
            "and d.specialization = :specialization " +
            "order by a.visitStart asc")
    List<AvailabilityDto> findAvailabilitiesForSpecialization(@Param("specialization") Specialization specialization);

    @Query("select new pl.edu.wat.wcy.dto.visit.AvailabilityDto (d.id, d.fullName.name.firstName, " +
            "d.fullName.name.surname, d.specialization, a.visitStart) " +
            "from Doctor as d " +
            "left join Availability as a on a.doctor.id = d.id " +
            "left join Visit as v on a.visitStart = v.visitStart " +
            "where v.visitStart is null " +
            "and d.fullName.name.firstName = :firstName " +
            "and d.fullName.name.surname = :surname " +
            "order by a.visitStart asc")
    List<AvailabilityDto> findAvailabilitiesForDoctor(@Param("firstName") String firstName,
                                                      @Param("surname") String surname);

    Optional<Availability> findByVisitStartAndDoctor(LocalDateTime visitStart, Doctor doctor);
}
