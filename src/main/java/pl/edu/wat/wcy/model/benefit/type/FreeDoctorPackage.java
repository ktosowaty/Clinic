package pl.edu.wat.wcy.model.benefit.type;

import pl.edu.wat.wcy.model.benefit.BenefitPackage;
import pl.edu.wat.wcy.model.benefit.Money;
import pl.edu.wat.wcy.model.person.doctor.Doctor;

import javax.persistence.*;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Entity
@DiscriminatorValue("free_doctor")
public class FreeDoctorPackage extends BenefitPackage {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    protected FreeDoctorPackage() {}

    public FreeDoctorPackage(String name, Money yearCost, String description, Doctor doctor) {
        super(name, yearCost, description);
        this.doctor = requireNonNull(doctor, "doctor");
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
