package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.disease.DiseaseDto;
import pl.edu.wat.wcy.dto.disease.DiseaseProjection;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.disease.Disease;
import pl.edu.wat.wcy.repository.DiseaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public DiseaseProjection findDisease(String name) {
        return diseaseRepository.findProjectedByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("disease", name));
    }

    public void saveDisease(DiseaseDto diseaseDto) {
        String name = diseaseDto.getName();
        checkIfDiseaseExist(name);
        String description = diseaseDto.getDescription();
        Disease disease = new Disease(name, description);
        diseaseRepository.save(disease);
    }

    public List<DiseaseProjection> findDiseases() {
        return diseaseRepository.findAllProjectedBy();
    }

    private void checkIfDiseaseExist(String name) {
        Optional<Disease> disease = diseaseRepository.findByName(name);
        if (disease.isPresent()) throw new IllegalArgumentException("Disease with given name already exists.");
    }
}
