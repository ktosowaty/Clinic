package pl.edu.wat.wcy.dto.visit;

import pl.edu.wat.wcy.model.person.doctor.Specialization;

import java.time.LocalDateTime;

public class AvailabilityDto {
    private long doctorId;

    private String doctorFirstName;

    private String doctorSurname;

    private Specialization specialization;

    private LocalDateTime visitStart;

    private AvailabilityDto() {}

    public AvailabilityDto(long doctorId, String doctorFirstName, String doctorSurname,
                           Specialization specialization, LocalDateTime visitStart) {
        this.doctorId = doctorId;
        this.doctorFirstName = doctorFirstName;
        this.doctorSurname = doctorSurname;
        this.specialization = specialization;
        this.visitStart = visitStart;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }
}
