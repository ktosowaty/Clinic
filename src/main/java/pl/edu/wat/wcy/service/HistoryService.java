package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.PatientDiseaseDto;
import pl.edu.wat.wcy.model.person.data.Pesel;
import pl.edu.wat.wcy.repository.PatientDiseaseRepository;

import java.util.List;

@Service
public class HistoryService {
    private final PatientDiseaseRepository patientDiseaseRepository;

    @Autowired
    public HistoryService(PatientDiseaseRepository patientDiseaseRepository) {
        this.patientDiseaseRepository = patientDiseaseRepository;
    }

    public List<PatientDiseaseDto> generatePatientHistory(String peselStr) {
        Pesel pesel = new Pesel(peselStr);
        return patientDiseaseRepository.findPatientDiseases(pesel);
    }
}
