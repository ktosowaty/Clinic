package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.dto.medicine.MedicineProjection;
import pl.edu.wat.wcy.model.disease.Medicine;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name);

    Optional<MedicineProjection> findProjectedByName(String name);
}
