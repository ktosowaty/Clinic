package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.opinion.OpinionRequestDto;
import pl.edu.wat.wcy.dto.opinion.OpinionResponseDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.opinion.Opinion;
import pl.edu.wat.wcy.dto.opinion.OpinionProjection;
import pl.edu.wat.wcy.model.opinion.Rate;
import pl.edu.wat.wcy.model.person.data.name.Name;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.model.person.user.User;
import pl.edu.wat.wcy.model.visit.Visit;
import pl.edu.wat.wcy.repository.*;
import pl.edu.wat.wcy.security.AuthenticatedUser;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public OpinionService(OpinionRepository opinionRepository, UserRepository userRepository,
                          DoctorRepository doctorRepository, VisitRepository visitRepository,
                          PatientRepository patientRepository) {
        this.opinionRepository = opinionRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
    }

    public List<OpinionProjection> findOpinionsForDoctor(String firstName, String surname) {
        Name name = new Name(firstName, surname);
        return opinionRepository.findAllByDoctorFullNameName(name);
    }

    public OpinionResponseDto saveOpinion(AuthenticatedUser user, OpinionRequestDto opinionRequestDto) {
        Opinion opinion = createOpinion(user, opinionRequestDto);
        checkIfPatientVisitedDoctor(opinion.getPatient(), opinion.getDoctor());
        checkIfOpinionExist(opinion.getPatient(), opinion.getDoctor());
        opinionRepository.save(opinion);
        return createResponse(opinion);
    }

    private Opinion createOpinion(AuthenticatedUser authenticatedUser, OpinionRequestDto opinionRequestDto) {
        User user = findUser(authenticatedUser.getId());
        Patient patient = findPatient(user.getPerson().getId());
        Doctor doctor = findDoctor(opinionRequestDto.getDoctorId());
        String description = opinionRequestDto.getOpinion();
        Rate rate = opinionRequestDto.getRate();
        return new Opinion(patient, doctor, description, rate);
    }

    private User findUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", userId));
    }

    private Patient findPatient(long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("patient", patientId));
    }

    private Doctor findDoctor(long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("doctor", doctorId));
    }

    private void checkIfPatientVisitedDoctor(Patient patient, Doctor doctor) {
        List<Visit> visits = visitRepository.findAllByPatientAndDoctor(patient, doctor);
        if (visits.isEmpty()) throw new IllegalArgumentException("Given patient didn't visit given doctor.");
    }

    private void checkIfOpinionExist(Patient patient, Doctor doctor) {
        Optional<Opinion> existingOpinion = opinionRepository.findByPatientAndDoctor(patient, doctor);
        if (existingOpinion.isPresent()) throw new IllegalArgumentException("This doctor was already judged by this patient.");
    }

    private OpinionResponseDto createResponse(Opinion opinion) {
        Name patientName = opinion.getPatient().getFullName().getName();
        Name doctorName = opinion.getDoctor().getFullName().getName();
        LocalDate filingDate = opinion.getFilingDate();
        String description = opinion.getOpinion();
        Rate rate = opinion.getRate();
        return new OpinionResponseDto(patientName, doctorName, filingDate, description, rate);
    }
}
