package pl.edu.wat.wcy.dto;

import java.time.LocalDateTime;

public class VisitDto {
    private long patientId;

    private long doctorId;

    private LocalDateTime visitStart;

    private long cost;

    private VisitDto() {}

    public VisitDto(long patientId, long doctorId, LocalDateTime visitStart, long cost) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitStart = visitStart;
        this.cost = cost;
    }

    public long getPatientId() {
        return patientId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public long getCost() {
        return cost;
    }
}
