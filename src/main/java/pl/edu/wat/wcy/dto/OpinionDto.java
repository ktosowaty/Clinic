package pl.edu.wat.wcy.dto;

import pl.edu.wat.wcy.model.opinion.Rate;

import java.time.LocalDate;

public class OpinionDto {
    private long patientId;

    private long doctorId;

    private LocalDate filingDate;

    private String opinion;

    private Rate rate;

    private OpinionDto() {}

    public OpinionDto(long patientId, long doctorId, LocalDate filingDate, String opinion, Rate rate) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.filingDate = filingDate;
        this.opinion = opinion;
        this.rate = rate;
    }

    public long getPatientId() {
        return patientId;
    }

    public long getDoctorId() {
        return doctorId;
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
}
