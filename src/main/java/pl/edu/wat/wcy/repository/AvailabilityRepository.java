package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.dto.AvailabilityDto;
import pl.edu.wat.wcy.model.person.doctor.Availability;
import pl.edu.wat.wcy.model.person.doctor.Specialization;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    @Query("select new pl.edu.wat.wcy.dto.AvailabilityDto (d.personalData.name.firstName, d.personalData.name.surname, " +
            "d.specialization, a.visitStart) " +
            "from Doctor as d, Availability as a " +
            "left join Visit v on a.visitStart = v.visitStart " +
            "where v.visitStart is null " +
            "and d.specialization = :specialization " +
            "order by a.visitStart desc")
    List<AvailabilityDto> findAvailabilitiesForSpecialization(Specialization specialization);

    @Query("select new pl.edu.wat.wcy.dto.AvailabilityDto (d.personalData.name.firstName, d.personalData.name.surname, " +
            "d.specialization, a.visitStart) " +
            "from Doctor as d, Availability as a " +
            "left join Visit v on a.visitStart=v.visitStart " +
            "where v.visitStart is null " +
            "and d.personalData.name.firstName = :firstName and d.personalData.name.surname = :surname " +
            "order by a.visitStart desc")
    List<AvailabilityDto> findAvailabilitiesForDoctor(String firstName, String surname);
}
