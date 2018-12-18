package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.dto.benefit.BenefitPackageProjection;
import pl.edu.wat.wcy.model.benefit.BenefitPackage;

import java.util.List;

@Repository
public interface BenefitPackageRepository extends JpaRepository<BenefitPackage, Long> {
    List<BenefitPackageProjection> findAllProjectedBy();
}
