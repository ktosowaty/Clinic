package pl.edu.wat.wcy.dto.visit;

public class PatientVisitRequestDto {
    private long doctorId;

    private String visitStartStr;

    private PatientVisitRequestDto() {}

    public PatientVisitRequestDto(long doctorId, String visitStartStr) {
        this.doctorId = doctorId;
        this.visitStartStr = visitStartStr;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public String getVisitStartStr() {
        return visitStartStr;
    }
}
