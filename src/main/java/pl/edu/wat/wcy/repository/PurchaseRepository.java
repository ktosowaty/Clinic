package pl.edu.wat.wcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.model.benefit.Purchase;
import pl.edu.wat.wcy.model.person.patient.Patient;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    // TODO: 17.12.2018 lepiej?
    // currentDate is always equal currentDate2
    List<Purchase> findAllByPatientAndFromDateBeforeAndToDateAfter(Patient patient,
                                                                   LocalDate currentDate, LocalDate currentDate2);
}
