package pl.edu.wat.wcy.dto.doctor;

import org.springframework.beans.factory.annotation.Value;
import pl.edu.wat.wcy.model.person.data.name.Name;

public interface DoctorProjection {
    long getId();

    @Value("#{target.fullName.name}")
    Name getName();
}
