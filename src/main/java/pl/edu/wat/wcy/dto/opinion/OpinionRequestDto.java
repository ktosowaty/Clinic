package pl.edu.wat.wcy.dto.opinion;

import pl.edu.wat.wcy.model.opinion.Rate;

public class OpinionRequestDto {
    private long doctorId;

    private String opinion;

    private Rate rate;

    private OpinionRequestDto() {}

    public OpinionRequestDto(long doctorId, String opinion, Rate rate) {
        this.doctorId = doctorId;
        this.opinion = opinion;
        this.rate = rate;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public String getOpinion() {
        return opinion;
    }

    public Rate getRate() {
        return rate;
    }
}
