package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.dto.history.PatientDiseaseDto;
import pl.edu.wat.wcy.model.disease.PatientDisease;
import pl.edu.wat.wcy.model.person.data.Pesel;

import java.util.List;

@Repository
public interface PatientDiseaseRepository extends JpaRepository<PatientDisease, Long> {
    @Query("select new pl.edu.wat.wcy.dto.history.PatientDiseaseDto (p.fullName.name.firstName, p.fullName.name.surname, " +
            "p.pesel, d.name, pd.diagnosis, pd.diagnosisDate, m.name, md.dosage) " +
            "from PatientDisease as pd " +
            "left join Patient as p on p.id = pd.patient.id " +
            "left join Disease as d on d.id = pd.disease.id " +
            "left join MedicineDisease as md on pd.id = md.patientDisease.id " +
            "left join Medicine as m on m.id = md.medicine.id " +
            "where p.pesel = :patientPesel " +
            "order by pd.diagnosisDate desc")
    List<PatientDiseaseDto> findPatientDiseases(@Param("patientPesel") Pesel patientPesel);
}
