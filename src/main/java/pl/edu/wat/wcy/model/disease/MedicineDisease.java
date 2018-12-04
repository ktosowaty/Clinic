package pl.edu.wat.wcy.model.disease;

import javax.persistence.*;
import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Table(name = "medicine_diseases")
public class MedicineDisease {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_disease_id")
    private PatientDisease patientDisease;

    @Column
    private String dosage;

    private MedicineDisease() {
        // JPA
    }

    public MedicineDisease(Medicine medicine, PatientDisease patientDisease, String dosage) {
        this.medicine = requireNonNull(medicine, "medicine");
        this.patientDisease = requireNonNull(patientDisease, "disease");
        this.dosage = requireNonNull(dosage, "dosage");
    }

    public long getId() {
        return id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public PatientDisease getPatientDisease() {
        return patientDisease;
    }

    public String getDosage() {
        return dosage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineDisease that = (MedicineDisease) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
