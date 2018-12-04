package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.dto.PatientDiseaseDto;
import pl.edu.wat.wcy.model.disease.PatientDisease;

import java.util.List;

@Repository
public interface PatientDiseaseRepository extends JpaRepository<PatientDisease, Long> {
    @Query("select new pl.edu.wat.wcy.dto.PatientDiseaseDto (p.personalData.name.firstName, p.personalData.name.surname, " +
            "p.personalData.pesel, d.name, pd.diagnosis, pd.diagnosisDate, m.name, md.dosage) " +
            "from Patient as p, Disease as d, PatientDisease as pd, Medicine as m, MedicineDisease as md " +
            "where p.personalData.pesel = :patientPesel " +
            "order by pd.diagnosisDate desc")
    List<PatientDiseaseDto> findPatientDiseases(@Param("patientPesel") String patientPesel);
}
