package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.medicine.MedicineDto;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.disease.Medicine;
import pl.edu.wat.wcy.repository.MedicineRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MedicineService {
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public MedicineDto findMedicine(String name) {
        return medicineRepository.findProjectedByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("medicine", name));
    }

    public void saveMedicine(MedicineDto medicineDto) {
        String name = medicineDto.getName();
        checkIfMedicineExist(name);
        String description = medicineDto.getDescription();
        String sideEffects = medicineDto.getSideEffects();
        Medicine medicine = new Medicine(name, description, sideEffects);
        medicineRepository.save(medicine);
    }

    private void checkIfMedicineExist(String name) {
        Optional<Medicine> medicine = medicineRepository.findByName(name);
        if (medicine.isPresent()) throw new IllegalArgumentException("Medicine with given name already exists.");
    }
}
