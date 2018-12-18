package pl.edu.wat.wcy.dto.visit;

import java.time.LocalDateTime;

public class VisitRequestDto {
    private long patientId;

    private long doctorId;

    private LocalDateTime visitStart;

    private VisitRequestDto() {}

    public VisitRequestDto(long patientId, long doctorId, LocalDateTime visitStart) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitStart = visitStart;
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
}
