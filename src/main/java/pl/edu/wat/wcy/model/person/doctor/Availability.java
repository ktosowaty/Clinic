package pl.edu.wat.wcy.model.person.doctor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Entity
@Table(name = "availabilities")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column
    private LocalDateTime visitStart;

    private Availability() {}

    public Availability(Doctor doctor, LocalDateTime visitStart) {
        this.doctor = requireNonNull(doctor, "doctor");
        this.visitStart = requireNonNull(visitStart, "visit start");
    }

    public long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Availability that = (Availability) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
