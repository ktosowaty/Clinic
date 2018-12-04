package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.model.opinion.Opinion;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.patient.Patient;
import pl.edu.wat.wcy.repository.OpinionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OpinionService {
    private final OpinionRepository opinionRepository;

    @Autowired
    public OpinionService(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    public List<Opinion> findOpinionsForDoctor(String surname) {
        return opinionRepository.findAllByDoctorPersonalDataNameSurname(surname);
    }

    public void saveOpinion(Opinion opinion) {
        Patient patient = opinion.getPatient();
        Doctor doctor = opinion.getDoctor();
        Optional<Opinion> existingOpinion = opinionRepository.findByPatientAndDoctor(patient, doctor);
        if (!existingOpinion.isPresent()) opinionRepository.save(opinion);
        throw new IllegalArgumentException("This doctor was already judged by this patient.");
    }
}
