package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.dto.disease.DiseaseProjection;
import pl.edu.wat.wcy.model.disease.Disease;

import java.util.Optional;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Optional<Disease> findByName(String name);

    Optional<DiseaseProjection> findProjectedByName(String name);
}
