package pl.edu.wat.wcy.model.disease;

import pl.edu.wat.wcy.model.person.patient.Patient;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Table(name = "patient_diseases")
public class PatientDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @Column
    private String diagnosis;

    @Column
    private LocalDate diagnosisDate;

    private PatientDisease() {
        // JPA
    }

    public PatientDisease(Patient patient, Disease disease, String diagnosis, LocalDate diagnosisDate) {
        this.patient = requireNonNull(patient, "patient");
        this.disease = requireNonNull(disease, "disease");
        this.diagnosis = diagnosis;
        this.diagnosisDate = requireNonNull(diagnosisDate, "diagnosis, date");
    }

    public long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Disease getDisease() {
        return disease;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public LocalDate getDiagnosisDate() {
        return diagnosisDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDisease that = (PatientDisease) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
