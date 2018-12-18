package pl.edu.wat.wcy.model.visit;

import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.person.doctor.Doctor;
import pl.edu.wat.wcy.model.person.patient.Patient;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Entity
@Table(name = "visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column
    private LocalDateTime visitStart;

    @Embedded
    private Money cost;

    private Visit() {}

    public Visit(Patient patient, Doctor doctor, LocalDateTime visitStart, Money cost) {
        this.patient = requireNonNull(patient, "patient");
        this.doctor = requireNonNull(doctor, "doctor");
        this.visitStart = requireNonNull(visitStart,"visit start");
        this.cost = requireNonNull(cost, "cost");
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

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public Money getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return id == visit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
