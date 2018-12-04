package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.AvailabilityDto;
import pl.edu.wat.wcy.model.Visit;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.doctor.Specialization;
import pl.edu.wat.wcy.repository.AvailabilityRepository;
import pl.edu.wat.wcy.repository.VisitRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {
    private final AvailabilityRepository availabilityRepository;
    private final VisitRepository visitRepository;

    @Autowired
    public VisitService(AvailabilityRepository availabilityRepository, VisitRepository visitRepository) {
        this.availabilityRepository = availabilityRepository;
        this.visitRepository = visitRepository;
    }

    public List<AvailabilityDto> findVisitDatesForSpecialization(Specialization specialization) {
        return availabilityRepository.findAvailabilitiesForSpecialization(specialization);
    }

    public List<AvailabilityDto> findVisitDatesForDoctor(String firstName, String surname) {
        return availabilityRepository.findAvailabilitiesForDoctor(firstName, surname);
    }

    public void reserveVisit(Visit visit) {
        Doctor doctor = visit.getDoctor();
        LocalDateTime visitStart = visit.getVisitStart();
        Optional<Visit> existingVisit = visitRepository.findByDoctorAndVisitStart(doctor, visitStart);
        if (!existingVisit.isPresent()) visitRepository.save(visit);
        else throw new IllegalArgumentException("Visit for doctor " + doctor.getPersonalData().getName().getSurname() +
                " at " + visitStart + " has already been reserved.");
    }
}
