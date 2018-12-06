package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.OpinionDto;
import pl.edu.wat.wcy.model.opinion.Opinion;
import pl.edu.wat.wcy.model.opinion.OpinionProjection;
import pl.edu.wat.wcy.model.person.data.Name;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.DoctorRepository;
import pl.edu.wat.wcy.repository.OpinionRepository;
import pl.edu.wat.wcy.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public OpinionService(OpinionRepository opinionRepository, PatientRepository patientRepository,
                          DoctorRepository doctorRepository) {
        this.opinionRepository = opinionRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<OpinionProjection> findOpinionsForDoctor(String firstName, String surname) {
        Name name = new Name(firstName, surname);
        return opinionRepository.findAllByDoctorFullNameName(name);
    }

    public void saveOpinion(OpinionDto opinionDto) {
        Patient patient = findPatient(opinionDto.getPatientId());
        Doctor doctor = findDoctor(opinionDto.getDoctorId());
        checkIfOpinionExist(patient, doctor);
        Opinion opinion = new Opinion(patient, doctor, opinionDto.getFilingDate(), opinionDto.getOpinion(), opinionDto.getRate());
        opinionRepository.save(opinion);
    }

    private Patient findPatient(long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (!patient.isPresent()) throw new IllegalArgumentException("Patient with given id doesn't exist.");
        return patient.get();
    }

    private Doctor findDoctor(long doctorId) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if (!doctor.isPresent()) throw new IllegalArgumentException("Doctor with given id doesn't exist.");
        return doctor.get();
    }

    private void checkIfOpinionExist(Patient patient, Doctor doctor) {
        Optional<Opinion> existingOpinion = opinionRepository.findByPatientAndDoctor(patient, doctor);
        if (existingOpinion.isPresent()) throw new IllegalArgumentException("This doctor was already judged by this patient.");
    }
}
