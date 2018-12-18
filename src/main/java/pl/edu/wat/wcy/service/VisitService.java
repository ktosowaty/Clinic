package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.visit.AvailabilityDto;
import pl.edu.wat.wcy.dto.visit.VisitRequestDto;
import pl.edu.wat.wcy.dto.visit.VisitResponseDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.benefit.BenefitPackage;
import pl.edu.wat.wcy.model.benefit.Purchase;
import pl.edu.wat.wcy.model.benefit.type.AllInOnePackage;
import pl.edu.wat.wcy.model.benefit.type.FreeDoctorPackage;
import pl.edu.wat.wcy.model.benefit.type.FreeSpecialistPackage;
import pl.edu.wat.wcy.model.visit.Visit;
import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.person.doctor.Availability;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.doctor.Specialization;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Service
public class VisitService {
    private final AvailabilityRepository availabilityRepository;
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public VisitService(AvailabilityRepository availabilityRepository, VisitRepository visitRepository,
                        DoctorRepository doctorRepository, PatientRepository patientRepository,
                        PurchaseRepository purchaseRepository) {
        this.availabilityRepository = availabilityRepository;
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public List<AvailabilityDto> findVisitDatesForSpecialization(Specialization specialization) {
        return availabilityRepository.findAvailabilitiesForSpecialization(specialization);
    }

    public List<AvailabilityDto> findVisitDatesForDoctor(String firstName, String surname) {
        return availabilityRepository.findAvailabilitiesForDoctor(firstName, surname);
    }

    public VisitResponseDto reserveVisit(VisitRequestDto visitRequestDto) {
        Patient patient = findPatient(visitRequestDto.getPatientId());
        Doctor doctor = findDoctor(visitRequestDto.getDoctorId());
        LocalDateTime visitStart = verifyVisitStart(visitRequestDto.getVisitStart(), doctor);
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
}
