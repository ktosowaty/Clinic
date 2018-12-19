package pl.edu.wat.wcy.dto.opinion;

import pl.edu.wat.wcy.model.opinion.Rate;
import pl.edu.wat.wcy.model.person.data.name.Name;

import java.time.LocalDate;

public class OpinionResponseDto {
    private Name patientName;

    private Name doctorName;

    private LocalDate filingDate;

    private String description;

    private Rate rate;

    private OpinionResponseDto() {}

    public OpinionResponseDto(Name patientName, Name doctorName, LocalDate filingDate, String description, Rate rate) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.filingDate = filingDate;
        this.description = description;
        this.rate = rate;
    }

    public Name getPatientName() {
        return patientName;
    }

    public Name getDoctorName() {
        return doctorName;
    }

    public LocalDate getFilingDate() {
        return filingDate;
    }

    public String getDescription() {
        return description;
    }

    public Rate getRate() {
        return rate;
    }
}
