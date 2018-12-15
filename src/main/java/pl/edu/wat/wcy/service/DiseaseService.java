package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.DiseaseDto;
import pl.edu.wat.wcy.dto.DiseaseProjection;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.disease.Disease;
import pl.edu.wat.wcy.repository.DiseaseRepository;

import java.util.Optional;

@Service
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public DiseaseProjection findDisease(String name) {
        Optional<DiseaseProjection> disease = diseaseRepository.findProjectedByName(name);
        if (!disease.isPresent()) throw new ResourceNotFoundException("disease", name);
        return disease.get();
    }

    public void saveDisease(DiseaseDto diseaseDto) {
        String name = diseaseDto.getName();
        checkIfDiseaseExist(name);
        String description = diseaseDto.getDescription();
        Disease medicine = new Disease(name, description);
        diseaseRepository.save(medicine);
    }

    private void checkIfDiseaseExist(String name) {
        Optional<Disease> disease = diseaseRepository.findByName(name);
        if (disease.isPresent()) throw new IllegalArgumentException("Disease with given name already exists.");
    }
}
