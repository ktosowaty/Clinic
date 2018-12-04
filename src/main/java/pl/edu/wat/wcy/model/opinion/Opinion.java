package pl.edu.wat.wcy.model.opinion;

import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.patient.Patient;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static pl.edu.wat.wcy.utils.Validator.requireNonNull;

@Entity
@Table(name = "opinions")
public class Opinion {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column
    private LocalDate filingDate;

    @Column
    private String opinion;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Rate rate;

    private Opinion() {
        // JPA
    }

    public Opinion(Patient patient, Doctor doctor, LocalDate filingDate, String opinion, Rate rate) {
        this.patient = requireNonNull(patient, "patient");
        this.doctor = requireNonNull(doctor, "doctor");
        this.filingDate = requireNonNull(filingDate, "filing date");
        this.opinion = opinion;
        this.rate = requireNonNull(rate, "rate");
    }

    public long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDate getFilingDate() {
        return filingDate;
    }

    public String getOpinion() {
        return opinion;
    }

    public Rate getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opinion opinion = (Opinion) o;
        return id == opinion.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
