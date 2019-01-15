package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.visit.AvailabilityDto;
import pl.edu.wat.wcy.dto.visit.PatientVisitRequestDto;
import pl.edu.wat.wcy.dto.visit.SecretaryVisitRequestDto;
import pl.edu.wat.wcy.dto.visit.VisitResponseDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.benefit.BenefitPackage;
import pl.edu.wat.wcy.model.benefit.Purchase;
import pl.edu.wat.wcy.model.benefit.type.AllInOnePackage;
import pl.edu.wat.wcy.model.benefit.type.FreeDoctorPackage;
import pl.edu.wat.wcy.model.benefit.type.FreeSpecialistPackage;
import pl.edu.wat.wcy.model.person.user.User;
import pl.edu.wat.wcy.model.visit.Visit;
import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.person.doctor.Availability;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.doctor.Specialization;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.*;
import pl.edu.wat.wcy.security.AuthenticatedUser;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Service
@Transactional
public class VisitService {
    private final AvailabilityRepository availabilityRepository;
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    @Autowired
    public VisitService(AvailabilityRepository availabilityRepository, VisitRepository visitRepository,
                        DoctorRepository doctorRepository, PatientRepository patientRepository,
                        PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.availabilityRepository = availabilityRepository;
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }

    public List<AvailabilityDto> findVisitDatesForSpecialization(Specialization specialization) {
        return availabilityRepository.findAvailabilitiesForSpecialization(specialization);
    }

    public List<AvailabilityDto> findVisitDatesForDoctor(String firstName, String surname) {
        return availabilityRepository.findAvailabilitiesForDoctor(firstName, surname);
    }

    public VisitResponseDto reserveVisitBySecretary(SecretaryVisitRequestDto request) {
        Patient patient = findPatient(request.getPatientId());
        Doctor doctor = findDoctor(request.getDoctorId());
        LocalDateTime visitStart = verifyVisitStart(request.getVisitStartStr(), doctor);
        Money cost = calculateCost(doctor, patient);
        Visit visit = new Visit(patient, doctor, visitStart, cost);
        visitRepository.save(visit);
        return new VisitResponseDto(patient.getFullName().getName(), doctor.getFullName().getName(), visitStart, cost);
    }

    public VisitResponseDto reserveVisitByPatient(AuthenticatedUser authenticatedUser, PatientVisitRequestDto request) {
        User user = findUser(authenticatedUser.getId());
        Patient patient = findPatient(user.getPerson().getId());
        Doctor doctor = findDoctor(request.getDoctorId());
        LocalDateTime visitStart = verifyVisitStart(request.getVisitStartStr(), doctor);
        Money cost = calculateCost(doctor, patient);
        Visit visit = new Visit(patient, doctor, visitStart, cost);
        visitRepository.save(visit);
        return new VisitResponseDto(patient.getFullName().getName(), doctor.getFullName().getName(), visitStart, cost);
    }

    private Patient findPatient(long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("patient", patientId));
    }

    private Doctor findDoctor(long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("doctor", doctorId));
    }

    private LocalDateTime verifyVisitStart(String visitStartStr, Doctor doctor) {
        requireNonNull(visitStartStr, "visit start");
        LocalDateTime visitStart = LocalDateTime.parse(visitStartStr);
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

    private Money calculateCost(Doctor doctor, Patient patient) {
        LocalDate currentDate = LocalDate.now();
        List<Purchase> activePackages = purchaseRepository
                .findAllByPatientAndFromDateBeforeAndToDateAfter(patient, currentDate, currentDate);
        for (Purchase purchase : activePackages) {
            BenefitPackage benefitPackage = purchase.getBenefitPackage();
            if (benefitPackage instanceof AllInOnePackage) return new Money(0);
            if (benefitPackage instanceof FreeSpecialistPackage && isSameSpecialization(doctor, purchase)) return new Money(0);
            if (benefitPackage instanceof FreeDoctorPackage && isSameDoctor(doctor, purchase)) return new Money(0);
        }
        return doctor.getVisitCost();
    }

    private boolean isSameSpecialization(Doctor doctor, Purchase purchase) {
        Specialization doctorSpecialization = doctor.getSpecialization();
        Specialization inPackage = ((FreeSpecialistPackage) purchase.getBenefitPackage()).getSpecialization();
        return doctorSpecialization == inPackage;
    }

    private boolean isSameDoctor(Doctor doctor, Purchase purchase) {
        return ((FreeDoctorPackage) purchase.getBenefitPackage()).getDoctor().equals(doctor);
    }

    private User findUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", userId));
    }
}
