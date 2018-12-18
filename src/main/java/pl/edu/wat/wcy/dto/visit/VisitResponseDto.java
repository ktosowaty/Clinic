package pl.edu.wat.wcy.dto.visit;

import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.person.data.name.Name;

import java.time.LocalDateTime;

public class VisitResponseDto {
    private Name patientName;

    private Name doctorName;

    private LocalDateTime visitStart;

    private Money cost;

    private VisitResponseDto() {}

    public VisitResponseDto(Name patientName, Name doctorName, LocalDateTime visitStart, Money cost) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.visitStart = visitStart;
        this.cost = cost;
    }

    public Name getPatientName() {
        return patientName;
    }

    public Name getDoctorName() {
        return doctorName;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public Money getCost() {
        return cost;
    }
}
