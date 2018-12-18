package pl.edu.wat.wcy.dto.opinion;

import org.springframework.beans.factory.annotation.Value;
import pl.edu.wat.wcy.model.opinion.Rate;
import pl.edu.wat.wcy.model.person.data.name.Name;

import java.time.LocalDate;

public interface OpinionProjection {
    @Value("#{target.patient.fullName.name}")
    Name getName();

    LocalDate getFilingDate();

    String getOpinion();

    Rate getRate();
}
