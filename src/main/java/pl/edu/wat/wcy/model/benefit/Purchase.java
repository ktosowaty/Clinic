package pl.edu.wat.wcy.model.benefit;

import pl.edu.wat.wcy.model.person.patient.Patient;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id")
    private BenefitPackage benefitPackage;

    @Column
    private LocalDate fromDate;

    @Column
    private LocalDate toDate;

    private Purchase() {}

    public Purchase(Patient patient, BenefitPackage benefitPackage, LocalDate fromDate, LocalDate toDate) {
        this.patient = requireNonNull(patient, "patient");
        this.benefitPackage = requireNonNull(benefitPackage, "benefitPackage");
        this.fromDate = requireNonNull(fromDate, "'from' date");
        this.toDate = requireNonNull(toDate, "'to' date");
    }

    public long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public BenefitPackage getBenefitPackage() {
        return benefitPackage;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
