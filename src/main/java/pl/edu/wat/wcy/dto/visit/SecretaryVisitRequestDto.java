package pl.edu.wat.wcy.dto.visit;

public class SecretaryVisitRequestDto {
    private long patientId;

    private long doctorId;

    private String visitStartStr;

    private SecretaryVisitRequestDto() {}

    public SecretaryVisitRequestDto(long patientId, long doctorId, String visitStartStr) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitStartStr = visitStartStr;
    }

    public long getPatientId() {
        return patientId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public String getVisitStartStr() {
        return visitStartStr;
    }
}
