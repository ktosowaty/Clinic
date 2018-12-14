package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.AvailabilityDto;
import pl.edu.wat.wcy.dto.VisitDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.visit.Visit;
import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.person.doctor.Availability;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.doctor.Specialization;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.AvailabilityRepository;
import pl.edu.wat.wcy.repository.DoctorRepository;
import pl.edu.wat.wcy.repository.PatientRepository;
import pl.edu.wat.wcy.repository.VisitRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Service
public class VisitService {
    private final AvailabilityRepository availabilityRepository;
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public VisitService(AvailabilityRepository availabilityRepository, VisitRepository visitRepository,
                        DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.availabilityRepository = availabilityRepository;
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<AvailabilityDto> findVisitDatesForSpecialization(Specialization specialization) {
        return availabilityRepository.findAvailabilitiesForSpecialization(specialization);
    }

    public List<AvailabilityDto> findVisitDatesForDoctor(String firstName, String surname) {
        return availabilityRepository.findAvailabilitiesForDoctor(firstName, surname);
    }

    public void reserveVisit(VisitDto visitDto) {
        Patient patient = findPatient(visitDto.getPatientId());
        Doctor doctor = findDoctor(visitDto.getDoctorId());
        LocalDateTime visitStart = verifyVisitStart(visitDto.getVisitStart(), doctor);
        Money cost = new Money(visitDto.getCost());
        Visit visit = new Visit(patient, doctor, visitStart, cost);
        visitRepository.save(visit);
    }

    private Patient findPatient(long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (!patient.isPresent()) throw new ResourceNotFoundException("patient", patientId);
        return patient.get();
    }

    private Doctor findDoctor(long doctorId) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (!doctor.isPresent()) throw new ResourceNotFoundException("doctor", doctorId);
        return doctor.get();
    }

    private LocalDateTime verifyVisitStart(LocalDateTime visitStart, Doctor doctor) {
        requireNonNull(visitStart, "visit start");
        checkAvailability(visitStart, doctor);
        checkNotReserved(visitStart, doctor);
        return visitStart;
    }

    private void checkAvailability(LocalDateTime visitStart, Doctor doctor) {
        Optional<Availability> availability = availabilityRepository.findByVisitStartAndDoctor(visitStart, doctor);
        if (!availability.isPresent()) throw new IllegalArgumentException("Doctor " + doctor.getFullName().getName()
                + " doesn't take patients at " + visitStart + ".");
    }

    private void checkNotReserved(LocalDateTime visitStart, Doctor doctor) {
        Optional<Visit> existingVisit = visitRepository.findByVisitStartAndDoctor(visitStart, doctor);
        if (existingVisit.isPresent()) throw new IllegalArgumentException("Visit for doctor "
                + doctor.getFullName().getName() + " at " + visitStart + " has already been reserved.");
    }
}
