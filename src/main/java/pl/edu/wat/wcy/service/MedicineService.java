package pl.edu.wat.wcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.dto.MedicineDto;
import pl.edu.wat.wcy.dto.MedicineProjection;
import pl.edu.wat.wcy.exception.ResourceNotFoundException;
import pl.edu.wat.wcy.model.disease.Medicine;
import pl.edu.wat.wcy.repository.MedicineRepository;

import java.util.Optional;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public MedicineProjection findMedicine(String name) {
        Optional<MedicineProjection> medicine = medicineRepository.findProjectedByName(name);
        if (!medicine.isPresent()) throw new ResourceNotFoundException("medicine", name);
        return medicine.get();
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
